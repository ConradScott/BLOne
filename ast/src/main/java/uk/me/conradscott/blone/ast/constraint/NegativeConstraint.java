package uk.me.conradscott.blone.ast.constraint;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class NegativeConstraint implements ConstraintIfc {
    private final LocationIfc m_location;
    private final ConstraintIfc m_constraint;

    public NegativeConstraint( final LocationIfc location, final ConstraintIfc constraint ) {
        m_location = location;
        m_constraint = constraint;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public ConstraintIfc getConstraint() {
        return m_constraint;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
