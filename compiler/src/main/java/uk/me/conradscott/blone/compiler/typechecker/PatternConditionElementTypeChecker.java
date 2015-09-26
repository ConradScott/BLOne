package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.conditionelement.PatternCE;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import javax.annotation.Nullable;

final class PatternConditionElementTypeChecker {
    private PatternConditionElementTypeChecker() {}

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final PatternCE conditionElement,
                              final SymbolTable symbolTable,
                              final ScopeIfc< RelationDecl > relationDecls )
    {
        return doCheck( errorCollector, null, conditionElement, symbolTable, relationDecls );
    }

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final IdentifierIfc captureVariable,
                              final PatternCE conditionElement,
                              final SymbolTable symbolTable,
                              final ScopeIfc< RelationDecl > relationDecls )
    {
        return doCheck( errorCollector, captureVariable, conditionElement, symbolTable, relationDecls );
    }

    private static SymbolTable doCheck( final ErrorCollectorIfc errorCollector,
                                        @Nullable final IdentifierIfc captureVariable,
                                        final PatternCE conditionElement,
                                        final SymbolTable symbolTable,
                                        final ScopeIfc< RelationDecl > relationDecls )
    {
        final String name = conditionElement.getName();

        @Nullable final RelationDecl relationDecl = relationDecls.get( name );

        if ( relationDecl == null ) {
            errorCollector.error( conditionElement.getLocation(), "Unknown relation '" + name + "' in pattern" );
            return symbolTable;
        }

        SymbolTable result = symbolTable;

        if ( captureVariable != null ) {
            result = VariableTypeChecker.check( errorCollector, captureVariable, result, relationDecl );
        }

        return AttributeConstraintTypeChecker.check( errorCollector, conditionElement.values(), result, relationDecl );
    }
}
