package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class CapturedCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final IdentifierIfc m_captureVariable;
    private final PatternCE m_patternCE;

    public CapturedCE( final LocationIfc location, final IdentifierIfc captureVariable, final PatternCE patternCE )
    {
        m_location = location;
        m_captureVariable = captureVariable;
        m_patternCE = patternCE;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public IdentifierIfc getCaptureVariable() {
        return m_captureVariable;
    }

    public PatternCE getPatternCE() {
        return m_patternCE;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
