package uk.me.conradscott.blone.ast.scope;

import com.gs.collections.api.RichIterable;

import javax.annotation.Nullable;

public interface ScopeIfc< V > extends Iterable< V > {
    @Nullable V get( String key );
    ScopeIfc< V > put( V value );

    default boolean contains( final String key ) {
        return get( key ) != null;
    }

    RichIterable< V > values();
}
