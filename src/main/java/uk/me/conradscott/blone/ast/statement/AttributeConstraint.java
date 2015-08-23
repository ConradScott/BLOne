package uk.me.conradscott.blone.ast.statement;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;

public final class AttributeConstraint implements LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @NotNull private final ConstraintIfc m_constraint;

    public AttributeConstraint( @NotNull final LocationIfc location,
                                @NotNull final String name,
                                @NotNull final ConstraintIfc constraint )
    {
        m_location = location;
        m_name = name;
        m_constraint = constraint;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull public ConstraintIfc getConstraint() {
        return m_constraint;
    }
}
