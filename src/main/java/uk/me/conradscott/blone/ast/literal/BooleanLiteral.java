package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class BooleanLiteral implements PrimitiveLiteralIfc< Boolean > {
    @NotNull private final LocationIfc m_location;
    private final boolean m_value;

    public BooleanLiteral( @NotNull final LocationIfc location, final boolean value ) {
        m_location = location;
        m_value = value;
    }

    @NotNull @Override public PrimitiveType getType() {
        return PrimitiveType.BOOLEAN;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull @Override public Boolean getValue() {
        return m_value;
    }
}
