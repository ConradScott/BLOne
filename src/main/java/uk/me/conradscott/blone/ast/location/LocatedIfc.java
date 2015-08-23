package uk.me.conradscott.blone.ast.location;

import org.jetbrains.annotations.NotNull;

public interface LocatedIfc {
    @NotNull LocationIfc getLocation();
}
