package uk.me.conradscott.blone.ast.attributeconstraint;

import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class SimpleAttributeConstraint implements AttributeConstraintIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final ConstraintIfc m_constraint;

    public SimpleAttributeConstraint( final LocationIfc location, final String name, final ConstraintIfc constraint ) {
        m_location = location;
        m_name = name;
        m_constraint = constraint;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public String getName() {
        return m_name;
    }

    public ConstraintIfc getConstraint() {
        return m_constraint;
    }

    @Override public < T, R > R accept( final AttributeConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
