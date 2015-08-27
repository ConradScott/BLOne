package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class UniversalCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final ConditionElementIfc m_range;
    private final ConditionElementIfc m_predicate;

    public UniversalCE( final LocationIfc location,
                        final ConditionElementIfc range,
                        final ConditionElementIfc predicate )
    {
        m_location = location;
        m_range = range;
        m_predicate = predicate;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public ConditionElementIfc getRange() {
        return m_range;
    }

    public ConditionElementIfc getPredicate() {
        return m_predicate;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
