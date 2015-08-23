package uk.me.conradscott.blone.ast.conditionelement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class UniversalCE implements ConditionElementIfc {
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

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConditionElementVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visit( this, t );
    }
}
