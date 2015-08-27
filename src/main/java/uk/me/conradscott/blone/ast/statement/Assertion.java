package uk.me.conradscott.blone.ast.statement;

import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Assertion implements LocatedIfc {
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
}
