package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public class Variable implements LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;

    public Variable( @NotNull final LocationIfc location, @NotNull final String name )
    {
        m_location = location;
        m_name = name;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public String getName() {
        return m_name;
    }
}