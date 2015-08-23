package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class VariableConstraint implements ConstraintIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final Variable m_variable;

    public VariableConstraint( @NotNull final LocationIfc location, @NotNull final Variable variable ) {
        m_location = location;
        m_variable = variable;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public Variable getVariable() {
        return m_variable;
    }

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConstraintVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visit( this, t );
    }
}
