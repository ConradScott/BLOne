package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class ExistentialCE implements ConditionElementIfc {
    private final LocationIfc m_location;
    private final ConditionElementIfc m_predicate;

    public ExistentialCE( final LocationIfc location, final ConditionElementIfc predicate ) {
        m_location = location;
        m_predicate = predicate;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public ConditionElementIfc getPredicate() {
        return m_predicate;
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
