package uk.me.conradscott.blone.ast.constraint;

import uk.me.conradscott.blone.ast.expression.ExpressionIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class ExpressionConstraint implements ConstraintIfc {
    private final LocationIfc m_location;
    private final ExpressionIfc m_expression;

    public ExpressionConstraint( final LocationIfc location, final ExpressionIfc expression ) {
        m_location = location;
        m_expression = expression;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public ExpressionIfc getExpression() {
        return m_expression;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
