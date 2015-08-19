package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public class Retraction implements LocatedIfc {
    private final LocationIfc m_location;
    private final PatternCE m_patternCE;

    public Retraction(@NotNull final LocationIfc location, @NotNull final PatternCE patternCE) {
        m_location = location;
        m_patternCE = patternCE;
    }

    @NotNull public PatternCE getPatternCE() {
        return m_patternCE;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }
}
