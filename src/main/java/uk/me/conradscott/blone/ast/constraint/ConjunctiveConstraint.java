package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class ConjunctiveConstraint implements ConstraintIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final List< ConstraintIfc > m_conjuncts;

    public ConjunctiveConstraint( @NotNull final LocationIfc location, @NotNull final List< ConstraintIfc > conjuncts )
    {
        m_location = location;
        m_conjuncts = conjuncts;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public List< ConstraintIfc > getConjuncts() {
        return m_conjuncts;
    }

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConstraintVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visit( this, t );
    }
}
