package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public class ExistentialCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final ConditionElementIfc m_predicate;

    public ExistentialCE(@NotNull final LocationIfc location, @NotNull final ConditionElementIfc predicate)
    {
        m_location = location;
        m_predicate = predicate;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public ConditionElementIfc getPredicate() {
        return m_predicate;
    }
}
