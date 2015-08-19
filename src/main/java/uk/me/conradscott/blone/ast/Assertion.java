package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public final class Assertion implements LocatedIfc {
    private final LocationIfc m_location;
    private final RelationExpr m_relationExpr;

    public Assertion(@NotNull final LocationIfc location, @NotNull final RelationExpr relationExpr) {
        m_location = location;
        m_relationExpr = relationExpr;
    }

    @NotNull public RelationExpr getRelationExpr() {
        return m_relationExpr;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }
}
