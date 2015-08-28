package uk.me.conradscott.blone.ast.action;

import uk.me.conradscott.blone.ast.expression.ExpressionIfc;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class AttributeExpr implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final ExpressionIfc m_expression;

    public AttributeExpr( final LocationIfc location, final String name, final ExpressionIfc expression ) {
        m_location = location;
        m_name = name;
        m_expression = expression;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    public ExpressionIfc getExpression() {
        return m_expression;
    }
}
