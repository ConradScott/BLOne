package uk.me.conradscott.blone.ast.location;

import java.util.Objects;
import javax.annotation.Nullable;

public final class Location implements LocationIfc {
    private final String m_sourceFile;
    private final int m_lineno;
    private final int m_column;

    /**
     * @param sourceFile
     * @param lineno     Line numbers start from one.
     * @param column     Column numbers start from one.
     */
    public Location( final String sourceFile, final int lineno, final int column ) {
        assert lineno > 0;
        assert column >= 0;

        m_sourceFile = sourceFile;
        m_lineno = lineno;
        m_column = column;
    }

    @Override public String getSourceFile() {
        return m_sourceFile;
    }

    @Override public int getLineno() {
        return m_lineno;
    }

    @Override public int getColumn() {
        return m_column;
    }

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(
            value = "NP_METHOD_PARAMETER_TIGHTENS_ANNOTATION",
            justification = "Default annotation is not correct for equals" ) @Override
    public boolean equals( @Nullable final Object obj ) {
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
