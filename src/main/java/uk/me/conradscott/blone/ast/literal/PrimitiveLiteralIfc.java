package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.expression.ExpressionIfc;
import uk.me.conradscott.blone.ast.expression.ExpressionVisitorIfc;

public interface PrimitiveLiteralIfc< P > extends ExpressionIfc {
    P getValue();

    < A, R > R accept( LiteralVisitorIfc< A, R > visitor, A a );

    @Override default < T, R > R accept( final ExpressionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
