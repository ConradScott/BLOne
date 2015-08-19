package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public interface ExpressionIfc extends LocatedIfc {
    @NotNull TypeIfc getType();
}
