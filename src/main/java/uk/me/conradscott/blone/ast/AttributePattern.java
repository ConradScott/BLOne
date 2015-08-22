package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public final class AttributePattern implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;

    public AttributePattern( @NotNull final LocationIfc location, @NotNull final String name ) {
        m_location = location;
        m_name = name;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }
}
