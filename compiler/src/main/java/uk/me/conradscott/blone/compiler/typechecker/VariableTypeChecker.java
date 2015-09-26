package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.declaration.DeclarationIfc;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.declaration.VariableDecl;
import uk.me.conradscott.blone.ast.type.TypeIfc;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import javax.annotation.Nullable;

final class VariableTypeChecker {
    private VariableTypeChecker() {}

    /**
     * Check that the given variable is compatible with the given type.
     *
     * @param errorCollector
     * @param variable
     * @param symbolTable
     * @param type
     */
    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final IdentifierIfc variable,
                              final SymbolTable symbolTable,
                              final TypeIfc type )
    {
        @Nullable final DeclarationIfc declaration = symbolTable.get( variable.getName() );

        if ( declaration == null ) {
            // This is the name's first use (in this scope) so this occurrence is a declaration.
            return symbolTable.put( new VariableDecl( variable, type ) );
        }

        // The name has already been declared (in this scope), so this occurrence is a use.
        TypeCompatibilityChecker.check( errorCollector, type, variable, declaration );

        return symbolTable;
    }
}
