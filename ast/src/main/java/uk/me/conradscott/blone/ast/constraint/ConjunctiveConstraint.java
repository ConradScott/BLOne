package uk.me.conradscott.blone.ast.constraint;

import org.eclipse.collections.api.list.ImmutableList;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class ConjunctiveConstraint implements ConstraintIfc {
    private final LocationIfc                    m_location;
    private final ImmutableList< ConstraintIfc > m_conjuncts;

    public ConjunctiveConstraint( final LocationIfc location, final ImmutableList< ConstraintIfc > conjuncts ) {
        m_location = location;
        m_conjuncts = conjuncts;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public ImmutableList< ConstraintIfc > getConjuncts() {
        return m_conjuncts;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
