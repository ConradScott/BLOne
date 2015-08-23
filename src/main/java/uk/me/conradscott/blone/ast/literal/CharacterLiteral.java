package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public class CharacterLiteral implements PrimitiveLiteralIfc< Character > {
    @NotNull private final LocationIfc m_location;
    private final char m_value;

    public CharacterLiteral( @NotNull final LocationIfc location, final char value ) {
        m_location = location;
        m_value = value;
    }

    @NotNull @Override public PrimitiveType getType() {
        return PrimitiveType.BOOLEAN;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull @Override public Character getValue() {
        return m_value;
    }
}
