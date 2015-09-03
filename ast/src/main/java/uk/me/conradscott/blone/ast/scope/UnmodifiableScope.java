package uk.me.conradscott.blone.ast.scope;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.Nullable;

final class UnmodifiableScope< K, V > implements ScopeIfc< K, V > {
    private final ScopeIfc< K, V > m_scope;

    static < K, V > ScopeIfc< K, V > instance( final ScopeIfc< K, V > scope ) {
        return new UnmodifiableScope< K, V >( scope );
    }

    private UnmodifiableScope( final ScopeIfc< K, V > scope ) {
        m_scope = scope;
    }

    @Nullable @Override public V get( final K key ) {
        return m_scope.get( key );
    }

    @Override public V put( final V value ) {
        throw new UnsupportedOperationException( "Insertion is not supported on an immutable scope." );
    }

    @Override public Iterator< V > iterator() {
        return m_scope.iterator();
    }

    @Override public void forEach( final Consumer< ? super V > action ) {
        m_scope.forEach( action );
    }

    @Override public Spliterator< V > spliterator() {
        return m_scope.spliterator();
    }
}
