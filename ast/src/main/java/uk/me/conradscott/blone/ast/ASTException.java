package uk.me.conradscott.blone.ast;

import uk.me.conradscott.blone.BLOneException;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public class ASTException extends BLOneException {
    private static final long serialVersionUID = -7485412181067210753L;

    private final LocationIfc m_location;

    public ASTException( final LocationIfc location, final String message ) {
        super( message );
        m_location = location;
    }

    public ASTException( final LocationIfc location, final Throwable cause ) {
        super( cause );
        m_location = location;
    }

    public ASTException( final LocationIfc location, final String message, final Throwable cause ) {
        super( message, cause );
        m_location = location;
    }

    public LocationIfc getLocation() {
        return m_location;
    }
}
