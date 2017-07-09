package uk.me.conradscott.blone.dentation;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

import org.antlr.v4.runtime.Token;

final class TokenStream {
    private final Queue< Token > m_queue = new LinkedList<>();

    private final Supplier< Token > m_supplier;

    TokenStream( final Supplier< Token > supplier ) {
        m_supplier = supplier;
    }

    /**
     * Retrieves and removes the head of this stream
     *
     * @return the head of this stream
     */
    Token poll() {
        @Nullable final Token poll = m_queue.poll();

        if ( poll != null ) {
            return poll;
        }

        final Token token = m_supplier.get();

        assert token != null : "The provided token supplier must not yield a null value";

        return token;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    Token peek() {
        @Nullable final Token peek = m_queue.peek();

        if ( peek != null ) {
            return peek;
        }

        final Token token = m_supplier.get();

        assert token != null : "The provided token supplier must not yield a null value";

        m_queue.add( token );

        return token;
    }
}
