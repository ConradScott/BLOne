package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public class NegationCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final ConditionElementIfc m_conditionElement;

    public NegationCE( @NotNull final LocationIfc location, @NotNull final ConditionElementIfc conditionElement )
    {
        m_location = location;
        m_conditionElement = conditionElement;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }
}
