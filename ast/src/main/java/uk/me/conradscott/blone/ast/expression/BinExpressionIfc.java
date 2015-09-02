package uk.me.conradscott.blone.ast.expression;

public interface BinExpressionIfc extends ExpressionIfc {
    BinOp op();

    ExpressionIfc left();

    ExpressionIfc right();
}
