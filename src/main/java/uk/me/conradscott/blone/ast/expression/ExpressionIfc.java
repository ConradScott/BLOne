package uk.me.conradscott.blone.ast.expression;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public interface ExpressionIfc extends LocatedIfc {
    @NotNull PrimitiveType getType();
}
