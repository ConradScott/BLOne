package uk.me.conradscott.blone.compiler;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public interface ErrorCollectorIfc {
    int getWarnings();
    void warning( LocationIfc location, String msg );

    int getErrors();
    void error( LocationIfc location, String msg );
}
