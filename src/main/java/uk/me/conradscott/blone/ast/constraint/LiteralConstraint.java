package uk.me.conradscott.blone.ast.constraint;

import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

public class LiteralConstraint implements ConstraintIfc {
    private final LocationIfc m_location;
    private final PrimitiveLiteralIfc< ? > m_literal;

    public LiteralConstraint( final LocationIfc location, final PrimitiveLiteralIfc< ? > literal ) {
        m_location = location;
        m_literal = literal;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public PrimitiveLiteralIfc< ? > getLiteral() {
        return m_literal;
    }

    @Override public < T, R > R accept( final ConstraintVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
