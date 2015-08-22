package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public final class AttributeDecl implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    @NotNull private final PrimitiveType m_type;

    public AttributeDecl(@NotNull final LocationIfc location,
                         @NotNull final String name,
                         @NotNull final PrimitiveType type)
    {
        m_location = location;
        m_name = name;
        m_type = type;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull public PrimitiveType getType() {
        return m_type;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }
}
