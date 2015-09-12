package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.declaration.DeclarationIfc;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;
import uk.me.conradscott.blone.ast.type.TypeIfc;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class TypeCompatibilityChecker {
    private TypeCompatibilityChecker() {}

    /**
     * Is the given variable use compatible with its declaration?
     *
     * @param errorCollector Error collector.
     * @param context        The occurrence type of the identifier.
     * @param variable       The identifier being used.
     * @param declaration    The declaration of this variable in the current scope.
     */
    static TypeIfc check( final ErrorCollectorIfc errorCollector,
                          final TypeIfc context,
                          final IdentifierIfc variable,
                          final DeclarationIfc declaration )
    {
        assert declaration.getName().equals( variable.getName() );

        if ( !compatible( context, declaration.getType() ) ) {
            errorCollector.error( variable.getLocation(),
                                  "Variable '"
                                  + variable.getName()
                                  + "' is declared to have type "
                                  + declaration.getType()
                                  + " but is being used in a context with type "
                                  + context );
        }

        return context;
    }

    static TypeIfc check( final ErrorCollectorIfc errorCollector,
                          final TypeIfc context,
                          final PrimitiveLiteralIfc< ? > literal )
    {
        if ( !compatible( context, literal.getType() ) ) {
            errorCollector.error( literal.getLocation(),
                                  "Literal '"
                                  + literal
                                  + "' has type "
                                  + literal.getType()
                                  + " but is being used in a context with type "
                                  + context );
        }

        return context;
    }

    static boolean compatible( final TypeIfc context, final TypeIfc type )
    {
        // TODO: Implement automatic type casts?

        return context.equals( type );
    }
}
