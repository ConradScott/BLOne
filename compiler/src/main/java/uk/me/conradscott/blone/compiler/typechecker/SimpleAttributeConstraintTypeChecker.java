package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.attributeconstraint.SimpleAttributeConstraint;
import uk.me.conradscott.blone.ast.declaration.DeclarationIfc;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import javax.annotation.Nullable;

final class SimpleAttributeConstraintTypeChecker {
    private SimpleAttributeConstraintTypeChecker() {}

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final SimpleAttributeConstraint attributeConstraint,
                              final SymbolTable symbolTable,
                              final RelationDecl relationDecl )
    {
        return doCheck( errorCollector, null, attributeConstraint, symbolTable, relationDecl );
    }

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final IdentifierIfc captureVariable,
                              final SimpleAttributeConstraint attributeConstraint,
                              final SymbolTable symbolTable,
                              final RelationDecl relationDecl )
    {
        return doCheck( errorCollector, captureVariable, attributeConstraint, symbolTable, relationDecl );
    }

    private static SymbolTable doCheck( final ErrorCollectorIfc errorCollector,
                                        @Nullable final IdentifierIfc captureVariable,
                                        final SimpleAttributeConstraint attributeConstraint,
                                        final SymbolTable symbolTable,
                                        final RelationDecl relationDecl )
    {
        final String name = attributeConstraint.getName();

        @Nullable final DeclarationIfc attributeDecl = relationDecl.get( name );

        if ( attributeDecl == null ) {
            errorCollector.error( attributeConstraint.getLocation(),
                                  "No such attribute '" + name + "' in relation '" + relationDecl.getName() + '\'' );
            return symbolTable;
        }

        SymbolTable result = symbolTable;

        if ( captureVariable != null ) {
            result = VariableTypeChecker.check( errorCollector, captureVariable, result, attributeDecl.getType() );
        }

        return ConstraintTypeChecker.check( errorCollector,
                                            attributeConstraint.getConstraint(),
                                            result,
                                            attributeDecl.getType() );
    }
}
