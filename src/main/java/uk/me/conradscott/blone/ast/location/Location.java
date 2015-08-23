package uk.me.conradscott.blone.ast.location;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Location implements LocationIfc {
    @NotNull private final String m_sourceFile;
    private final int m_lineno;
    private final int m_column;

    /**
     * @param sourceFile
     * @param lineno     Line numbers start from one.
     * @param column     Column numbers start from one.
     */
    public Location( @NotNull final String sourceFile, final int lineno, final int column ) {
        assert lineno > 0;
        assert column > 0;

        m_sourceFile = sourceFile;
        m_lineno = lineno;
        m_column = column;
    }

    @NotNull @Override public String getSourceFile() {
        return m_sourceFile;
    }

    @Override public int getLineno() {
        return m_lineno;
    }

    @Override public int getColumn() {
        return m_column;
    }

    @Override public boolean equals( final Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( ( obj == null ) || ( getClass() != obj.getClass() ) ) {
            return false;
        }
        final Location that = ( Location ) obj;
        return Objects.equals( m_lineno, that.m_lineno ) &&
               Objects.equals( m_column, that.m_column ) &&
               Objects.equals( m_sourceFile, that.m_sourceFile );
    }

    @Override public int hashCode() {
        return Objects.hash( m_lineno, m_column, m_sourceFile );
    }

    @Override public String toString() {
        return m_sourceFile + ':' + m_lineno + ':' + m_column;
    }
}
