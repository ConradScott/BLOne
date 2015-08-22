package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public class UniversalCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final ConditionElementIfc m_range;
    @NotNull private final ConditionElementIfc m_predicate;

    public UniversalCE( @NotNull final LocationIfc location,
                        @NotNull final ConditionElementIfc range,
                        @NotNull final ConditionElementIfc predicate )
    {
        m_location = location;
        m_range = range;
        m_predicate = predicate;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public ConditionElementIfc getRange() {
        return m_range;
    }

    @NotNull public ConditionElementIfc getPredicate() {
        return m_predicate;
    }
}
