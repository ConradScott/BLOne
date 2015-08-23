package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class LongIntegerLiteral implements PrimitiveLiteralIfc< Long > {
    @NotNull private final LocationIfc m_location;
    private final long m_value;

    public LongIntegerLiteral( @NotNull final LocationIfc location, final long value ) {
        m_location = location;
        m_value = value;
    }

    @NotNull @Override public PrimitiveType getType() {
        return PrimitiveType.LONG;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull @Override public Long getValue() {
        return m_value;
    }
}
