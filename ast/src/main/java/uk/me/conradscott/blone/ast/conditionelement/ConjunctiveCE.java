package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class ConjunctiveCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final List< ConditionElementIfc > m_conjuncts;

    public ConjunctiveCE( final LocationIfc location, final List< ConditionElementIfc > conditionElements )    {
        m_location = location;
        m_conjuncts = conditionElements;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public List< ConditionElementIfc > getConjuncts() {
        return m_conjuncts;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
