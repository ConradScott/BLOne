package uk.me.conradscott.blone.ast.constraint;

import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class DisjunctiveConstraint implements ConstraintIfc {
    private final LocationIfc m_location;
    private final List< ConstraintIfc > m_disjuncts;

    public DisjunctiveConstraint( final LocationIfc location, final List< ConstraintIfc > disjuncts ) {
        m_location = location;
        m_disjuncts = disjuncts;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public List< ConstraintIfc > getDisjuncts() {
        return m_disjuncts;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
