package uk.me.conradscott.blone.compiler;

import uk.me.conradscott.blone.ast.location.Location;

public interface ErrorCollectorIfc {
    void warning( Location location, String msg );
    void error( Location location, String msg );
}
