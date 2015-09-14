package uk.me.conradscott.blone.ast.action;

import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Retraction implements ActionIfc {
    private final LocationIfc m_location;
    private final Variable m_variable;

    public Retraction( final LocationIfc location, final Variable variable ) {
        m_location = location;
        m_variable = variable;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public Variable getVariable() {
        return m_variable;
    }

    @Override public < T, R > R accept( final ActionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
