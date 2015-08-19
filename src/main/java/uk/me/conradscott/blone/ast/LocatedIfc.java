package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public interface LocatedIfc {
    @NotNull LocationIfc getLocation();
}
