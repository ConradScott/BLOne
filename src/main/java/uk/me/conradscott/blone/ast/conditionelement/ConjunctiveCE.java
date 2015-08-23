package uk.me.conradscott.blone.ast.conditionelement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class ConjunctiveCE implements ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final List< ConditionElementIfc > m_conjuncts;

    public ConjunctiveCE( @NotNull final LocationIfc location,
                          @NotNull final List< ConditionElementIfc > conditionElements )
    {
        m_location = location;
        m_conjuncts = conditionElements;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public List< ConditionElementIfc > getConjuncts() {
        return m_conjuncts;
    }

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConditionElementVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visitConjunctiveCE( this, t );
    }
}
