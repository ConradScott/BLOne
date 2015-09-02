package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class NegativeCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final ConditionElementIfc m_conditionElement;

    public NegativeCE( final LocationIfc location, final ConditionElementIfc conditionElement ) {
        m_location = location;
        m_conditionElement = conditionElement;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
