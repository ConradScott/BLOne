package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ScopeIfc< K, V > extends Iterable< V > {
    @Nullable V put( @NotNull final V value );

    @Nullable V get( @NotNull final K key );
}
