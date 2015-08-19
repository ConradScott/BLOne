package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DisjunctionCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final List<ConditionElementIfc> m_disjuncts;

    public DisjunctionCE(@NotNull final LocationIfc location, @NotNull final List<ConditionElementIfc> disjuncts)
    {
        m_location = location;
        m_disjuncts = disjuncts;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public List<ConditionElementIfc> getDisjuncts() {
        return m_disjuncts;
    }
}
