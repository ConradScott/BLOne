package uk.me.conradscott.blone.ast.attributeconstraint;

import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class CapturedAttributeConstraint implements AttributeConstraintIfc {
    private final LocationIfc m_location;
    private final IdentifierIfc m_captureVariable;
    private final SimpleAttributeConstraint m_attributeConstraint;

    public CapturedAttributeConstraint( final LocationIfc location,
                                        final IdentifierIfc captureVariable,
                                        final SimpleAttributeConstraint attributeConstraint )
    {
        m_location = location;
        m_captureVariable = captureVariable;
        m_attributeConstraint = attributeConstraint;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public String getName() {
        return m_attributeConstraint.getName();
    }

    public IdentifierIfc getCaptureVariable() {
        return m_captureVariable;
    }

    public SimpleAttributeConstraint getAttributeConstraint() {
        return m_attributeConstraint;
    }

    @Override public < T, R > R accept( final AttributeConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
