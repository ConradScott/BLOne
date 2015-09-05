package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class FloatingPointLiteral implements PrimitiveLiteralIfc< Float > {
    private final LocationIfc m_location;
    private final float m_value;

    public FloatingPointLiteral( final LocationIfc location, final float value ) {
        m_location = location;
        m_value = value;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public PrimitiveType getType() {
        return PrimitiveType.FLOAT;
    }

    @Override public Float getValue() {
        return m_value;
    }

    public float floatValue() {
        return m_value;
    }

    @Override public < A, R > R accept( final LiteralVisitorIfc< A, R > visitor, final A a ) {
        return visitor.visit( this, a );
    }
}
