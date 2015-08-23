package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class FloatingPointLiteral implements PrimitiveLiteralIfc< Float > {
    @NotNull private final LocationIfc m_location;
    private final float m_value;

    public FloatingPointLiteral( @NotNull final LocationIfc location, final float value ) {
        m_location = location;
        m_value = value;
    }

    @NotNull @Override public PrimitiveType getType() {
        return PrimitiveType.FLOAT;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull @Override public Float getValue() {
        return m_value;
    }
}
