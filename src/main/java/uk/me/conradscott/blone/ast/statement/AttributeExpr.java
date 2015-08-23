package uk.me.conradscott.blone.ast.statement;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.expression.ExpressionIfc;

public final class AttributeExpr implements LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @NotNull private final ExpressionIfc m_expression;

    public AttributeExpr( @NotNull final LocationIfc location,
                          @NotNull final String name,
                          @NotNull final ExpressionIfc expression )
    {
        m_location = location;
        m_name = name;
        m_expression = expression;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull public ExpressionIfc getExpression() {
        return m_expression;
    }
}
