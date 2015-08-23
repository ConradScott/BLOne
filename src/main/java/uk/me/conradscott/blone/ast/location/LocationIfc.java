package uk.me.conradscott.blone.ast.location;

import org.jetbrains.annotations.NotNull;

public interface LocationIfc {
    @NotNull String getSourceFile();

    int getLineno();

    int getColumn();
}
