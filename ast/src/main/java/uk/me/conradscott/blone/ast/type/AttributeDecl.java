package uk.me.conradscott.blone.ast.type;

import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class AttributeDecl implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final PrimitiveType m_type;

    public AttributeDecl( final LocationIfc location, final String name, final PrimitiveType type ) {
        m_location = location;
        m_name = name;
        m_type = type;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    public PrimitiveType getType() {
        return m_type;
    }
}
