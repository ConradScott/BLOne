package uk.me.conradscott.blone.ast.expression;

import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public interface ExpressionIfc extends LocatedIfc {
    PrimitiveType getType();
}
