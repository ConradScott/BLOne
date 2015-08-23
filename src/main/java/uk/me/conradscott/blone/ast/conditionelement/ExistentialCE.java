package uk.me.conradscott.blone.ast.conditionelement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class ExistentialCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final ConditionElementIfc m_predicate;

    public ExistentialCE( @NotNull final LocationIfc location, @NotNull final ConditionElementIfc predicate )
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

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConditionElementVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visitExistentialCE( this, t );
    }
}
