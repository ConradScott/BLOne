package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class DoubleFloatingPointLiteral implements PrimitiveLiteralIfc< Double > {
    private final LocationIfc m_location;
    private final double m_value;

    public DoubleFloatingPointLiteral( final LocationIfc location, final double value ) {
        m_location = location;
        m_value = value;
    }

    @Override public PrimitiveType getType() {
        return PrimitiveType.DOUBLE;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public Double getValue() {
        return m_value;
    }

    @Override public < A, R > R accept( final LiteralVisitorIfc< A, R > visitor, final A a ) {
        return visitor.visit( this, a );
    }
}
