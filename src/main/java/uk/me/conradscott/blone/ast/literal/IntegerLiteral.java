package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class IntegerLiteral implements PrimitiveLiteralIfc< Integer > {
    @NotNull private final LocationIfc m_location;
    private final int m_value;

    public IntegerLiteral( @NotNull final LocationIfc location, final int value ) {
        m_location = location;
        m_value = value;
    }

    @NotNull @Override public PrimitiveType getType() {
        return PrimitiveType.INT;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull @Override public Integer getValue() {
        return m_value;
    }

    @Nullable @Override
    public < A, R > R accept( @NotNull final LiteralVisitorIfc< A, R > visitor, @NotNull final A a ) {
        return visitor.visit( this, a );
    }
}
