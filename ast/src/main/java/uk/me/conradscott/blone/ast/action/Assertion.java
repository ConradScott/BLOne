package uk.me.conradscott.blone.ast.action;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Assertion implements ActionIfc {
    private final LocationIfc m_location;
    private final TupleExpr m_tupleExpr;

    public Assertion( final LocationIfc location, final TupleExpr tupleExpr ) {
        m_location = location;
        m_tupleExpr = tupleExpr;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public TupleExpr getTupleExpr() {
        return m_tupleExpr;
    }

    @Override public < T, R > R accept( final ActionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
