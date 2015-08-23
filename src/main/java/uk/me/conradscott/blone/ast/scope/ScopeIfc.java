package uk.me.conradscott.blone.ast.scope;

import org.jetbrains.annotations.NotNull;

public interface ScopeIfc< K, V > extends Iterable< V > {
    @NotNull V put( @NotNull final V value );

    @NotNull V get( @NotNull final K key );
}
