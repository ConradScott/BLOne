package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.expression.ExpressionIfc;

public interface PrimitiveLiteralIfc< T > extends ExpressionIfc {
    @NotNull T getValue();

    @Nullable < A, R > R accept( @NotNull LiteralVisitorIfc< A, R > visitor, @NotNull A a );
}
