package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class LongIntegerLiteral implements PrimitiveLiteralIfc< Long > {
    private final LocationIfc m_location;
    private final long m_value;

    public LongIntegerLiteral( final LocationIfc location, final long value ) {
        m_location = location;
        m_value = value;
    }

    @Override public PrimitiveType getType() {
        return PrimitiveType.LONG;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public Long getValue() {
        return m_value;
    }

    @Override public < A, R > R accept( final LiteralVisitorIfc< A, R > visitor, final A a ) {
        return visitor.visit( this, a );
    }
}
