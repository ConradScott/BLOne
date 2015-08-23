package uk.me.conradscott.blone.ast.conditionelement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class DisjunctiveCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final List< ConditionElementIfc > m_disjuncts;

    public DisjunctiveCE( @NotNull final LocationIfc location, @NotNull final List< ConditionElementIfc > disjuncts )
    {
        m_location = location;
        m_disjuncts = disjuncts;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public List< ConditionElementIfc > getDisjuncts() {
        return m_disjuncts;
    }

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConditionElementVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visitDisjunctiveCE( this, t );
    }
}
