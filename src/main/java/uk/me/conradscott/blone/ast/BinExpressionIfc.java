package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public interface BinExpressionIfc extends ExpressionIfc {
    @NotNull ExpressionIfc left();

    @NotNull ExpressionIfc right();
}
