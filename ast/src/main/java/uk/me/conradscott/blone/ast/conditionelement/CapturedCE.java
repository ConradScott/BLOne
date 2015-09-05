package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class CapturedCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final Variable m_captureVariable;
    private final PatternCE m_patternCE;

    public CapturedCE( final LocationIfc location, final Variable captureVariable, final PatternCE patternCE )
    {
        m_location = location;
        m_captureVariable = captureVariable;
        m_patternCE = patternCE;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public Variable getCaptureVariable() {
        return m_captureVariable;
    }

    public PatternCE getPatternCE() {
        return m_patternCE;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
