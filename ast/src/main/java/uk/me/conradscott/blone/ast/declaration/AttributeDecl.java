package uk.me.conradscott.blone.ast.declaration;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class AttributeDecl implements DeclarationIfc {
    private final LocationIfc m_location;
    private final IdentifierIfc m_identifier;
    private final PrimitiveType m_type;

    public AttributeDecl( final LocationIfc location, final IdentifierIfc identifier, final PrimitiveType type ) {
        m_location = location;
        m_identifier = identifier;
        m_type = type;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public IdentifierIfc getIdentifier() {
        return m_identifier;
    }

    @Override public String getName() {
        return m_identifier.getName();
    }

    @Override public PrimitiveType getType() {
        return m_type;
    }
}
