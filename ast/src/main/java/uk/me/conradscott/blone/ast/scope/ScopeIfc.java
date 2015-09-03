package uk.me.conradscott.blone.ast.scope;

public interface ScopeIfc< K, V > extends Iterable< V > {
    V get( final K key );
    V put( final V value );
}
