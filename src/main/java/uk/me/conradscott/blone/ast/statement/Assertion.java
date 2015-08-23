package uk.me.conradscott.blone.ast.statement;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public final class Assertion implements LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final RelationExpr m_relationExpr;

    public Assertion( @NotNull final LocationIfc location, @NotNull final RelationExpr relationExpr ) {
        m_location = location;
        m_relationExpr = relationExpr;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public RelationExpr getRelationExpr() {
        return m_relationExpr;
    }
}
