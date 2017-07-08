package uk.me.conradscott.blone.ast.scope;

import javax.annotation.Nullable;

import org.eclipse.collections.api.RichIterable;

public interface ScopeIfc< V > extends Iterable< V > {
    @Nullable V get( String key );
    ScopeIfc< V > put( V value );

    default boolean contains( final String key ) {
        return get( key ) != null;
    }

    RichIterable< V > values();
}
