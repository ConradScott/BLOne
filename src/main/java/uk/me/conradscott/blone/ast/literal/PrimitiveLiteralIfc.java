package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.expression.ExpressionIfc;

public interface PrimitiveLiteralIfc< T > extends ExpressionIfc {
    T getValue();

    < A, R > R accept( LiteralVisitorIfc< A, R > visitor, A a );
}
