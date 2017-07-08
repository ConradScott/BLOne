package uk.me.conradscott.blone.ast.conditionelement;

import org.eclipse.collections.api.RichIterable;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class DisjunctiveCE implements ConditionElementIfc {
    private final LocationIfc                         m_location;
    private final RichIterable< ConditionElementIfc > m_disjuncts;

    public DisjunctiveCE( final LocationIfc location, final RichIterable< ConditionElementIfc > disjuncts ) {
        m_location = location;
        m_disjuncts = disjuncts;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public RichIterable< ConditionElementIfc > getDisjuncts() {
        return m_disjuncts;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
