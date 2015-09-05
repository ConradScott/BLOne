package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class IntegerLiteral implements PrimitiveLiteralIfc< Integer > {
    private final LocationIfc m_location;
    private final int m_value;

    public IntegerLiteral( final LocationIfc location, final int value ) {
        m_location = location;
        m_value = value;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public PrimitiveType getType() {
        return PrimitiveType.INT;
    }

    @Override public Integer getValue() {
        return m_value;
    }

    public int intValue() {
        return m_value;
    }

    @Override public < A, R > R accept( final LiteralVisitorIfc< A, R > visitor, final A a ) {
        return visitor.visit( this, a );
    }
}
