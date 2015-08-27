package uk.me.conradscott.blone.ast.statement;

import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class AttributeConstraint implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final ConstraintIfc m_constraint;

    public AttributeConstraint( final LocationIfc location, final String name, final ConstraintIfc constraint ) {
        m_location = location;
        m_name = name;
        m_constraint = constraint;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    public ConstraintIfc getConstraint() {
        return m_constraint;
    }
}
