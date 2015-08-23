package uk.me.conradscott.blone.ast.expression;

import org.jetbrains.annotations.NotNull;

public interface BinExpressionIfc extends ExpressionIfc {
    @NotNull BinOp op();

    @NotNull ExpressionIfc left();

    @NotNull ExpressionIfc right();
}
