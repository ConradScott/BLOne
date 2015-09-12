package uk.me.conradscott.blone.ast.type;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface TypeVisitorIfc< T, R > {
    default List< R > visit( final Collection< ? extends TypeIfc > actions, final T t ) {
        return actions.stream().map( type -> visit( type, t ) ).collect( Collectors.toList() );
    }

    default R visit( final TypeIfc type, final T t ) {
        return type.accept( this, t );
    }

    R visit( PrimitiveType primitiveType, T t );
    R visit( RelationDecl relationDecl, T t );
    R visit( PartialType partialType, T t );
    R visit( InconsistentType inconsistentType, T t );
}
