package uk.me.conradscott.blone.ast.conditionelement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class CapturedCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final Variable m_variable;
    @NotNull private final ConditionElementIfc m_conditionElement;

    public CapturedCE( @NotNull final LocationIfc location,
                       @NotNull final Variable variable,
                       @NotNull final ConditionElementIfc conditionElement )
    {
        m_location = location;
        m_variable = variable;
        m_conditionElement = conditionElement;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public Variable getVariable() {
        return m_variable;
    }

    @NotNull public ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConditionElementVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visit( this, t );
    }
}
