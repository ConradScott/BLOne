package uk.me.conradscott.blone.ast.scope;

import javax.annotation.Nullable;

public interface ScopeIfc< K, V > extends Iterable< V > {
    @Nullable V get( final K key );
    V put( final V value );
}
