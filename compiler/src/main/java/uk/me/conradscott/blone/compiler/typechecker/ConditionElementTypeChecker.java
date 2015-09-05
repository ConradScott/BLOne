package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.conditionelement.CapturedCE;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementVisitorIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.DisjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.ExistentialCE;
import uk.me.conradscott.blone.ast.conditionelement.NegativeCE;
import uk.me.conradscott.blone.ast.conditionelement.PatternCE;
import uk.me.conradscott.blone.ast.conditionelement.UniversalCE;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import javax.annotation.Nullable;

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
            return symbolTable;
        }

        @Override public SymbolTable visit( final ConjunctiveCE conditionElement, final SymbolTable symbolTable ) {
            return conditionElement.getConjuncts()
                                   .stream()
                                   .reduce( symbolTable,
                                            ( previous, ce ) -> visit( ce, previous ),
                                            ( previous, current ) -> current );
        }

        @Override public SymbolTable visit( final DisjunctiveCE conditionElement, final SymbolTable symbolTable ) {
            return symbolTable;
        }

        @Override public SymbolTable visit( final ExistentialCE conditionElement, final SymbolTable symbolTable ) {
            return symbolTable;
        }

        @Override public SymbolTable visit( final NegativeCE conditionElement, final SymbolTable symbolTable ) {
            return symbolTable;
        }

        @Override public SymbolTable visit( final PatternCE conditionElement, final SymbolTable symbolTable ) {
            final String name = conditionElement.getName();
            @Nullable final RelationDecl relationDecl = m_relationDecls.get( name );

            if ( relationDecl == null ) {
                m_errorCollector.error( conditionElement.getLocation(), "Unknown relation '" + name + "' in pattern" );
                return symbolTable;
            }

            return AttributeConstraintTypeChecker.check( m_errorCollector,
                                                         conditionElement,
                                                         symbolTable,
                                                         relationDecl );
        }

        @Override public SymbolTable visit( final UniversalCE conditionElement, final SymbolTable symbolTable ) {
            return symbolTable;
        }
    }
}
