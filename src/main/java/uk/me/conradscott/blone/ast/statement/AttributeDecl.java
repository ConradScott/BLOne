package uk.me.conradscott.blone.ast.statement;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class AttributeDecl implements LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @NotNull private final PrimitiveType m_type;

    public AttributeDecl( @NotNull final LocationIfc location,
                          @NotNull final String name,
                          @NotNull final PrimitiveType type )
    {
        m_location = location;
        m_name = name;
        m_type = type;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull public PrimitiveType getType() {
        return m_type;
    }
}
