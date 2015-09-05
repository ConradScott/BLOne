package uk.me.conradscott.blone.ast.scope;

import javax.annotation.Nullable;

public interface ScopeIfc< V > extends Iterable< V > {
    @Nullable V get( String key );
    V put( V value );
}
