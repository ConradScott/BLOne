package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class NegativeConstraint implements ConstraintIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final ConstraintIfc m_constraint;

    public NegativeConstraint( @NotNull final LocationIfc location, @NotNull final ConstraintIfc constraint )
    {
        m_location = location;
        m_constraint = constraint;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public ConstraintIfc getConstraint() {
        return m_constraint;
    }

    @NotNull @Override public < T, R > R accept( @NotNull final ConstraintVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visitNegativeConstraint( this, t );
    }
}
