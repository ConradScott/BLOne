package uk.me.conradscott.blone.ast.expression;

import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Variable implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;

    public Variable( final LocationIfc location, final String name ) {
        m_location = location;
        m_name = name;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }
}
