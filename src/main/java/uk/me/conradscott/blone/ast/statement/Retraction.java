package uk.me.conradscott.blone.ast.statement;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.conditionelement.PatternCE;

public final class Retraction implements LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final PatternCE m_patternCE;

    public Retraction( @NotNull final LocationIfc location, @NotNull final PatternCE patternCE ) {
        m_location = location;
        m_patternCE = patternCE;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public PatternCE getPatternCE() {
        return m_patternCE;
    }
}
