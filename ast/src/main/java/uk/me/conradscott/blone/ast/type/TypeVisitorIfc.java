package uk.me.conradscott.blone.ast.type;

import org.eclipse.collections.api.RichIterable;

public interface TypeVisitorIfc< T, R > {
    default RichIterable< R > visit( final RichIterable< ? extends TypeIfc > actions, final T t ) {
        return actions.collect( type -> visit( type, t ) );
    }

    default R visit( final TypeIfc type, final T t ) {
        return type.accept( this, t );
    }

    R visit( PrimitiveType primitiveType, T t );
    R visit( RelationDecl relationDecl, T t );
    R visit( PartialType partialType, T t );
    R visit( InconsistentType inconsistentType, T t );
}
