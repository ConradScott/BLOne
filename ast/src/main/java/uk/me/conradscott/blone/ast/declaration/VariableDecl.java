package uk.me.conradscott.blone.ast.declaration;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.TypeIfc;

public final class VariableDecl implements DeclarationIfc {
    private final IdentifierIfc m_identifier;
    private final TypeIfc m_type;

    public VariableDecl( final IdentifierIfc identifier, final TypeIfc type ) {
        m_identifier = identifier;
        m_type = type;
    }

    @Override public LocationIfc getLocation() {
        return m_identifier.getLocation();
    }

    @Override public IdentifierIfc getIdentifier() {
        return m_identifier;
    }

    @Override public String getName() {
        return m_identifier.getName();
    }

    @Override public TypeIfc getType() {
        return m_type;
    }
}
