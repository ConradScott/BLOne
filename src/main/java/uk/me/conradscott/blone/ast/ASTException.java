package uk.me.conradscott.blone.ast;

import uk.me.conradscott.blone.compiler.BLOneException;

public class ASTException extends BLOneException {
    private static final long serialVersionUID = -7485412181067210753L;

    public ASTException() { }

    public ASTException( final String message ) {
        super( message );
    }

    public ASTException( final Throwable cause ) {
        super( cause );
    }

    public ASTException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
