package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;

public class LiteralConstraint implements ConstraintIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final PrimitiveLiteralIfc< ? > m_literal;

    public LiteralConstraint( @NotNull final LocationIfc location, @NotNull final PrimitiveLiteralIfc< ? > literal )
    {
        m_location = location;
        m_literal = literal;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public PrimitiveLiteralIfc< ? > getLiteral() {
        return m_literal;
    }

    @NotNull @Override public < T, R > R accept( @NotNull final ConstraintVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visitLiteralConstraint( this, t );
    }
}
