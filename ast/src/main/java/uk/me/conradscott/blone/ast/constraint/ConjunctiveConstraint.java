package uk.me.conradscott.blone.ast.constraint;

import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class ConjunctiveConstraint implements ConstraintIfc {
    private final LocationIfc m_location;
    private final List< ConstraintIfc > m_conjuncts;

    public ConjunctiveConstraint( final LocationIfc location, final List< ConstraintIfc > conjuncts ) {
        m_location = location;
        m_conjuncts = conjuncts;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public List< ConstraintIfc > getConjuncts() {
        return m_conjuncts;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
