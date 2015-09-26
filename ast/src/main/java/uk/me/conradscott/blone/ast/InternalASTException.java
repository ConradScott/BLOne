package uk.me.conradscott.blone.ast;

import uk.me.conradscott.blone.BLOneException;

public class InternalASTException extends BLOneException {
    private static final long serialVersionUID = 3891757667345008901L;

    public InternalASTException( final String message ) {
        super( message );
    }

    public InternalASTException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
