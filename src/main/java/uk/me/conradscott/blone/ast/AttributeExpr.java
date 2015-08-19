package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public final class AttributeExpr implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final ExpressionIfc m_expression;

    public AttributeExpr(@NotNull final LocationIfc location,
                         @NotNull final String name,
                         @NotNull final ExpressionIfc expression)
    {
        m_location = location;
        m_name = name;
        m_expression = expression;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull public ExpressionIfc getExpression() {
        return m_expression;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }
}
