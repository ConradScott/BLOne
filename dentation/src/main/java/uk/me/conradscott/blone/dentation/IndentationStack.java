package uk.me.conradscott.blone.dentation;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

final class IndentationStack {
    private final Deque< Integer > m_stack = new LinkedList<>();

    IndentationStack() {
        // This zero indent is never removed from the indent stack.
        m_stack.push( 0 );
    }

    /**
     * Pushes an indentation onto the stack represented by this deque (in other words, at the head of this deque)
     * if it is possible to do so immediately without violating capacity restrictions, throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * @param indentation the indentation to push
     */
    void push( final int indentation ) {
        assertConsistency();

        assert indentation > m_stack.peek();

        m_stack.push( indentation );

        assertConsistency();
    }

    /**
     * Pops an indentation from the stack represented by this deque.  In other words, removes and returns the
     * first element of this deque.
     *
     * @return the indentation at the front of this deque (which is the top of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */
    int pop() {
        assertConsistency();

        assert m_stack.peek() > 0;

        final int indentation = m_stack.pop();

        assertConsistency();

        return indentation;
    }

    /**
     * Retrieves, but does not remove, the head of the queue represented by this deque (in other words, the first
     * element of this deque), or returns {@code null} if this deque is empty.
     *
     * @return the head of the queue represented by this deque, or {@code null} if this deque is empty
     */
    int peek() {
        assertConsistency();

        return m_stack.peek();
    }

    private void assertConsistency() {
        assert !m_stack.isEmpty();

        // Two part solution (using Guava for {@link Comparators}):
        // assert m_stack.stream().min( Integer::compare ).map( min -> min == 0 ).orElse( false );
        // assert Comparators.isInStrictOrder( m_stack, Integer::compare );

        Integer previous = -1;

        for ( final Integer indentation : m_stack ) {
            assert indentation > previous;
            previous = indentation;
        }
    }
}
