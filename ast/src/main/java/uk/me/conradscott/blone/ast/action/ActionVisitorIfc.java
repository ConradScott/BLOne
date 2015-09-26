package uk.me.conradscott.blone.ast.action;

import com.gs.collections.api.RichIterable;

public interface ActionVisitorIfc< T, R > {
    default RichIterable< R > visit( final RichIterable< ? extends ActionIfc > actions, final T t ) {
        return actions.collect( action -> visit( action, t ) );
    }

    default R visit( final ActionIfc action, final T t ) {
        return action.accept( this, t );
    }

    R visit( Assertion assertion, T t );
    R visit( Retraction retraction, T t );
    R visit( Modification modification, T t );
    R visit( Println println, T t );
}
