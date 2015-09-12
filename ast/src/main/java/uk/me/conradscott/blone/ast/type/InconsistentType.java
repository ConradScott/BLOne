package uk.me.conradscott.blone.ast.type;

import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;

public final class InconsistentType implements TypeIfc {
    private final IdentifierIfc m_identifier;

    public InconsistentType( final IdentifierIfc identifier )
    {
        m_identifier = identifier;
    }

    @Override public String getName() {
        return m_identifier.getName();
    }

    @Override public String toString() {
        return "<inconsistent>";
    }

    @Override public < T, R > R accept( final TypeVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
