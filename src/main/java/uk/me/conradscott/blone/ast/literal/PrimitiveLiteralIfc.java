package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.expression.ExpressionIfc;

public interface PrimitiveLiteralIfc< T > extends ExpressionIfc {
    @NotNull T getValue();
}
