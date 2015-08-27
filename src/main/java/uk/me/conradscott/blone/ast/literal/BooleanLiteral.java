package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class BooleanLiteral implements PrimitiveLiteralIfc< Boolean > {
    private final LocationIfc m_location;
    private final boolean m_value;

    public BooleanLiteral( final LocationIfc location, final boolean value ) {
        m_location = location;
        m_value = value;
    }

    @Override public PrimitiveType getType() {
        return PrimitiveType.BOOLEAN;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public Boolean getValue() {
        return m_value;
    }

    @Override public < A, R > R accept( final LiteralVisitorIfc< A, R > visitor, final A a ) {
        return visitor.visit( this, a );
    }
}
