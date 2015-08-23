package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class StringLiteral implements PrimitiveLiteralIfc< String > {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_value;

    public StringLiteral( @NotNull final LocationIfc location, @NotNull final String value ) {
        m_location = location;
        m_value = value;
    }

    @NotNull @Override public PrimitiveType getType() {
        return PrimitiveType.BOOLEAN;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull @Override public String getValue() {
        return m_value;
    }
}
