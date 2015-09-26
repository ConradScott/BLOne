package uk.me.conradscott.blone.ast.constraint;

import com.gs.collections.api.list.ImmutableList;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class DisjunctiveConstraint implements ConstraintIfc {
    private final LocationIfc m_location;
    private final ImmutableList< ConstraintIfc > m_disjuncts;

    public DisjunctiveConstraint( final LocationIfc location, final ImmutableList< ConstraintIfc > disjuncts ) {
        m_location = location;
        m_disjuncts = disjuncts;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public ImmutableList< ConstraintIfc > getDisjuncts() {
        return m_disjuncts;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
