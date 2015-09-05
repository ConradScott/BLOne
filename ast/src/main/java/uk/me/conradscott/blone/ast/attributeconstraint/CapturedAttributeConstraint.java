package uk.me.conradscott.blone.ast.attributeconstraint;

import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class CapturedAttributeConstraint implements AttributeConstraintIfc {
    private final LocationIfc m_location;
    private final Variable m_variable;
    private final SimpleAttributeConstraint m_attributeConstraint;

    public CapturedAttributeConstraint( final LocationIfc location,
                                        final Variable variable,
                                        final SimpleAttributeConstraint attributeConstraint )
    {
        m_location = location;
        m_variable = variable;
        m_attributeConstraint = attributeConstraint;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public String getName() {
        return m_attributeConstraint.getName();
    }

    public Variable getVariable() {
        return m_variable;
    }

    public SimpleAttributeConstraint getAttributeConstraint() {
        return m_attributeConstraint;
    }

    @Override public < T, R > R accept( final AttributeConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
