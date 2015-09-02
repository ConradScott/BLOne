package uk.me.conradscott.blone.ast.scope;

public interface ScopeIfc< K, V > extends Iterable< V > {
    V put( final V value );

    V get( final K key );
}
