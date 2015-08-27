package uk.me.conradscott.blone.ast.constraint;

import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class CapturedConstraint implements ConstraintIfc {
    private final LocationIfc m_location;
    private final Variable m_variable;
    private final ConstraintIfc m_constraint;

    public CapturedConstraint( final LocationIfc location, final Variable variable, final ConstraintIfc constraint ) {
        m_location = location;
        m_variable = variable;
        m_constraint = constraint;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public Variable getVariable() {
        return m_variable;
    }

    public ConstraintIfc getConstraint() {
        return m_constraint;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
