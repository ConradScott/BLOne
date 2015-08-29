package uk.me.conradscott.blone.compiler.builder;

final class StringCursor {
    private final String m_string;
    private int m_index;
    private final int m_end;

    StringCursor( final String s, final int start, final int end ) {
        assert start >= 0 : "The start position must be non-negative.";
        assert start <= end : "The start position must not be past the end position.";
        assert end <= s.length() : "The end position must not be past the end of the string.";

        m_string = s;
        m_index = start;
        m_end = end;
    }

    StringCursor( final String s ) {
        this( s, 0, s.length() );
    }

    int getIndex() {
        return m_index;
    }

    int getEnd() {
        return m_end;
    }

    boolean atEnd() {
        return m_index == m_end;
    }

    char next() {
        assert !atEnd() : "Attempt to read past end of string.";

        final char c = m_string.charAt( m_index );
        m_index += 1;
        return c;
    }

    char peek() {
        assert !atEnd() : "Attempt to read past end of string.";

        return m_string.charAt( m_index );
    }
}
