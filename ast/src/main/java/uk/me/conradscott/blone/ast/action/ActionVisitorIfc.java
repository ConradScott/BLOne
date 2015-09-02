package uk.me.conradscott.blone.ast.action;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ActionVisitorIfc< T, R > {
    default List< R > visit( final Collection< ? extends ActionIfc > actions, final T t ) {
        return actions.stream().map( action -> visit( action, t ) ).collect( Collectors.toList() );
    }

    default R visit( final ActionIfc action, final T t ) {
        return action.accept( this, t );
    }

    R visit( Assertion assertion, T t );

    R visit( Retraction retraction, T t );
}
