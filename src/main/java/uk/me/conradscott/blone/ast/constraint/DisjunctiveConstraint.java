package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class DisjunctiveConstraint implements ConstraintIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final List< ConstraintIfc > m_disjuncts;

    public DisjunctiveConstraint( @NotNull final LocationIfc location, @NotNull final List< ConstraintIfc > disjuncts )
    {
        m_location = location;
        m_disjuncts = disjuncts;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public List< ConstraintIfc > getDisjuncts() {
        return m_disjuncts;
    }

    @NotNull @Override public < T, R > R accept( @NotNull final ConstraintVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visitDisjunctiveConstraint( this, t );
    }
}
