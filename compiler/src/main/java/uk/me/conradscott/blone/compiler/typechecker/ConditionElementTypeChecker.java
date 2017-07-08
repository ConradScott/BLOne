package uk.me.conradscott.blone.compiler.typechecker;

import javax.annotation.Nullable;
import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;

import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.conditionelement.CapturedCE;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementVisitorIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.DisjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.ExistentialCE;
import uk.me.conradscott.blone.ast.conditionelement.NegativeCE;
import uk.me.conradscott.blone.ast.conditionelement.PatternCE;
import uk.me.conradscott.blone.ast.conditionelement.UniversalCE;
import uk.me.conradscott.blone.ast.declaration.DeclarationIfc;
import uk.me.conradscott.blone.ast.declaration.Identifier;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.declaration.VariableDecl;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.type.InconsistentType;
import uk.me.conradscott.blone.ast.type.PartialType;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.ast.type.TypeIfc;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class ConditionElementTypeChecker {
    private ConditionElementTypeChecker() {}

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final ConditionElementIfc conditionElementIfc,
                              final SymbolTable symbolTable,
                              final ScopeIfc< RelationDecl > relationDecls )
    {
        return new Visitor( errorCollector, relationDecls ).visit( conditionElementIfc, symbolTable );
    }

    private static final class Visitor implements ConditionElementVisitorIfc< SymbolTable, SymbolTable > {
        private final ErrorCollectorIfc m_errorCollector;
        private final ScopeIfc< RelationDecl > m_relationDecls;

        private Visitor( final ErrorCollectorIfc errorCollector, final ScopeIfc< RelationDecl > relationDecls ) {
            m_errorCollector = errorCollector;
            m_relationDecls = relationDecls;
        }

        @Override public SymbolTable visit( final CapturedCE conditionElement, final SymbolTable symbolTable ) {
            return PatternConditionElementTypeChecker.check( m_errorCollector,
                                                             conditionElement.getCaptureVariable(),
                                                             conditionElement.getPatternCE(),
                                                             symbolTable,
                                                             m_relationDecls );
        }

        @Override public SymbolTable visit( final PatternCE conditionElement, final SymbolTable symbolTable ) {
            return PatternConditionElementTypeChecker.check( m_errorCollector,
                                                             conditionElement,
                                                             symbolTable,
                                                             m_relationDecls );
        }

        @Override public SymbolTable visit( final NegativeCE conditionElement, final SymbolTable symbolTable ) {
            visit( conditionElement.getConditionElement(), symbolTable );
            return symbolTable;
        }

        @Override public SymbolTable visit( final ConjunctiveCE conditionElement, final SymbolTable symbolTable ) {
            return conditionElement.getConjuncts()
                                   .injectInto( symbolTable, ( state, conjunct ) -> visit( conjunct, state ) );
        }

        @Override public SymbolTable visit( final DisjunctiveCE conditionElement, final SymbolTable symbolTable ) {
            final RichIterable< ConditionElementIfc > disjuncts = conditionElement.getDisjuncts();

            assert !disjuncts.isEmpty() : "There must be at least one disjunct.";

            final RichIterable< SymbolTable > symbolTables = disjuncts.collect( disjunct -> visit( disjunct,
                                                                                                   symbolTable ) );

            // Collect all symbol names that are *introduced* in any of the disjuncts.
            final RichIterable< String > symbols = symbolTables.flatCollect( SymbolTable::values )
                                                               .collect( VariableDecl::getName )
                                                               .reject( symbolTable::contains )
                                                               .toSet();

            return symbols.injectInto( symbolTable, ( state, name ) -> {
                final IdentifierIfc identifier = new Identifier( conditionElement.getLocation(), name );
                final TypeIfc type = mostSpecificType( symbolTables, identifier );
                final VariableDecl decl = new VariableDecl( identifier, type );

                SymbolTable result = state;

                try {
                    result = result.put( decl );
                } catch ( final ASTException e ) {
                    m_errorCollector.error( e.getLocation(), e.getMessage() );
                }

                return result;
            } );
        }

        private static TypeIfc mostSpecificType( final RichIterable< SymbolTable > symbolTables,
                                                 final IdentifierIfc identifier )
        {
            final String symbol = identifier.getName();

            if ( symbolTables.anySatisfy( symbolTable -> symbolTable.get( symbol ) == null ) ) {
                return new PartialType( identifier );
            }

            final Iterator< SymbolTable > iterator = symbolTables.iterator();

            assert iterator.hasNext() : "There must be at least one disjunct.";

            final TypeIfc type;

            {
                final ScopeIfc< VariableDecl > first = iterator.next();

                @Nullable final DeclarationIfc variableDecl = first.get( symbol );

                assert variableDecl != null : "This symbol is defined everywhere.";

                type = variableDecl.getType();
            }

            while ( iterator.hasNext() ) {
                final ScopeIfc< VariableDecl > symbolTable = iterator.next();

                @Nullable final DeclarationIfc variableDecl = symbolTable.get( symbol );

                assert variableDecl != null : "This symbol is defined everywhere.";

                if ( !TypeCompatibilityChecker.compatible( type, variableDecl.getType() ) ) {
                    return new InconsistentType( identifier );
                }
            }

            return type;
        }

        @Override public SymbolTable visit( final ExistentialCE conditionElement, final SymbolTable symbolTable ) {
            visit( conditionElement.getPredicate(), symbolTable );
            return symbolTable;
        }

        @Override public SymbolTable visit( final UniversalCE conditionElement, final SymbolTable symbolTable ) {
            visit( conditionElement.getRange(), symbolTable );
            visit( conditionElement.getPredicate(), symbolTable );
            return symbolTable;
        }
    }
}
