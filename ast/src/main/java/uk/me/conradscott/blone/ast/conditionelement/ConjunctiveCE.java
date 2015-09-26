package uk.me.conradscott.blone.ast.conditionelement;

import com.gs.collections.api.RichIterable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class ConjunctiveCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final RichIterable< ConditionElementIfc > m_conjuncts;

    public ConjunctiveCE( final LocationIfc location, final RichIterable< ConditionElementIfc > conditionElements ) {
        m_location = location;
        m_conjuncts = conditionElements;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public RichIterable< ConditionElementIfc > getConjuncts() {
        return m_conjuncts;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
