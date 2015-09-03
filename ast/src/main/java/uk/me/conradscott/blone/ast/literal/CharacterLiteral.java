package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class CharacterLiteral implements PrimitiveLiteralIfc< Character > {
    private final LocationIfc m_location;
    private final char m_value;

    public CharacterLiteral( final LocationIfc location, final char value ) {
        m_location = location;
        m_value = value;
    }

    @Override public PrimitiveType getType() {
        return PrimitiveType.BOOLEAN;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public Character getValue() {
        return m_value;
    }

    public char charValue() {
        return m_value;
    }

    @Override public < A, R > R accept( final LiteralVisitorIfc< A, R > visitor, final A a ) {
        return visitor.visit( this, a );
    }
}
