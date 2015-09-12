package uk.me.conradscott.blone.ast.scope;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;

final class UnmodifiableScope< V > implements ScopeIfc< V > {
    private final ScopeIfc< V > m_scope;

    static < V > ScopeIfc< V > instance( final ScopeIfc< V > scope ) {
        return new UnmodifiableScope<>( scope );
    }

    private UnmodifiableScope( final ScopeIfc< V > scope ) {
        m_scope = scope;
    }

    @Nullable @Override public V get( final String key ) {
        return m_scope.get( key );
    }

    @Override public V put( final V value ) {
        throw new UnsupportedOperationException( "Insertion is not supported on an immutable scope." );
    }

    @Override public Stream< V > stream() {
        return m_scope.stream();
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
