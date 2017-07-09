package uk.me.conradscott.blone.dentation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import org.antlr.v4.runtime.Token;

final class TokenQueue {
    private final Queue< Token > m_queue = new LinkedList<>();

    /**
     * Inserts the specified token into this queue, returning {@code true}.
     *
     * @param token the token to add
     * @return {@code true} (as specified by {@link Collection#add})
     */
    boolean add( final Token token ) {
        return m_queue.add( token );
    }

    /**
     * Retrieves and removes the head of this queue, or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Nullable
    Token poll() {
        return m_queue.poll();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Nullable
    Token peek() {
        return m_queue.peek();
    }

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    boolean isEmpty() {
        return m_queue.isEmpty();
    }
}
