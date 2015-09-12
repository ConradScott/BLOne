package uk.me.conradscott.blone.ast.declaration;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Identifier implements IdentifierIfc {
    private final LocationIfc m_location;
    private final String m_name;

    public Identifier( final LocationIfc location, final String name ) {
        m_location = location;
        m_name = name;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public String getName() {
        return m_name;
    }
}
