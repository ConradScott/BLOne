package uk.me.conradscott.blone.ast.action;

import uk.me.conradscott.blone.ast.conditionelement.PatternCE;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Retraction implements ActionIfc {
    private final LocationIfc m_location;
    private final PatternCE m_patternCE;

    public Retraction( final LocationIfc location, final PatternCE patternCE ) {
        m_location = location;
        m_patternCE = patternCE;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public PatternCE getPatternCE() {
        return m_patternCE;
    }

    @Override public < T, R > R accept( final ActionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
