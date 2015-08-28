package uk.me.conradscott.blone.ast.action;

import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Assertion implements ActionIfc {
    private final LocationIfc m_location;
    private final RelationExpr m_relationExpr;

    public Assertion( final LocationIfc location, final RelationExpr relationExpr ) {
        m_location = location;
        m_relationExpr = relationExpr;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public RelationExpr getRelationExpr() {
        return m_relationExpr;
    }

    @Override public < T, R > R accept( final ActionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
