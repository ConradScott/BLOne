package uk.me.conradscott.blone.ast.statement;

import uk.me.conradscott.blone.ast.conditionelement.PatternCE;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Retraction implements LocatedIfc {
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
}
