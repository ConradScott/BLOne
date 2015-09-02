package uk.me.conradscott.blone.compiler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.text.MessageFormat;

final class ErrorCollector implements ErrorCollectorIfc {
    private final Logger m_logger;
    private int m_warnings = 0;
    private int m_errors = 0;

    ErrorCollector( final Logger logger ) {
        m_logger = logger;
    }

    @Override public void warning( final LocationIfc location, final String msg ) {
        output( location, msg, Level.WARN );
        m_warnings += 1;
    }

    @Override public void error( final LocationIfc location, final String msg ) {
        output( location, msg, Level.ERROR );
        m_errors += 1;
    }

    int getWarnings() {
        return m_warnings;
    }

    int getErrors() {
        return m_errors;
    }

    private void output( final LocationIfc location, final String msg, final Level level ) {
        m_logger.log( level, location + ": " + msg );
    }

    void reportErrors( final Logger logger ) {
        final MessageFormat format = new MessageFormat( "{0} {0,choice,0#errors|1#error|1<errors}." );
        logger.error( format.format( new Object[] { m_errors } ) );
    }
}
