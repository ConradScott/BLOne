package uk.me.conradscott.blone.ast.expression;

import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

public final class Variable implements ExpressionIfc {
    private final LocationIfc m_location;
    private final String m_name;

    public Variable( final LocationIfc location, final String name ) {
        m_location = location;
        m_name = name;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    @Override public PrimitiveType getType() {
        throw new ASTException( "No type assigned yet" );
    }

    @Override public < T, R > R accept( final ExpressionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
