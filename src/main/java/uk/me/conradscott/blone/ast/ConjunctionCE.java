package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ConjunctionCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final List<ConditionElementIfc> m_conjuncts;

    public ConjunctionCE(@NotNull final LocationIfc location,
                         @NotNull final List<ConditionElementIfc> conditionElements)
    {
        m_location = location;
        m_conjuncts = conditionElements;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public List<ConditionElementIfc> getConjuncts() {
        return m_conjuncts;
    }
}
