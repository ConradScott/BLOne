package uk.me.conradscott.blone.compiler;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public interface ErrorCollectorIfc {
    void warning( LocationIfc location, String msg );
    void error( LocationIfc location, String msg );
}
