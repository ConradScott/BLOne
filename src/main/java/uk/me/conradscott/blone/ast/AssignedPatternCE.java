package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public class AssignedPatternCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final Variable m_variable;
    @NotNull private final ConditionElementIfc m_conditionElement;

    public AssignedPatternCE( @NotNull final LocationIfc location,
                              @NotNull final Variable variable,
                              @NotNull final ConditionElementIfc conditionElement )
    {
        m_location = location;
        m_variable = variable;
        m_conditionElement = conditionElement;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public Variable getVariable() {
        return m_variable;
    }

    @NotNull public ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }
}
