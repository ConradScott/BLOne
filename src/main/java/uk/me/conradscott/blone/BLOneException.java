package uk.me.conradscott.blone;

public class BLOneException extends RuntimeException {
    private static final long serialVersionUID = 6709875246757423001L;

    public BLOneException() {
    }

    public BLOneException( final String message ) {
        super( message );
    }

    public BLOneException( final Throwable cause ) {
        super( cause );
    }

    public BLOneException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
