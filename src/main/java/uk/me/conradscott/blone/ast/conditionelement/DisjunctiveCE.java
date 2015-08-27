package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;

public final class DisjunctiveCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final List< ConditionElementIfc > m_disjuncts;

    public DisjunctiveCE( final LocationIfc location, final List< ConditionElementIfc > disjuncts ) {
        m_location = location;
        m_disjuncts = disjuncts;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public List< ConditionElementIfc > getDisjuncts() {
        return m_disjuncts;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
