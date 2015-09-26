package uk.me.conradscott.blone.ast.expression;

import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.declaration.DeclarationIfc;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.type.TypeIfc;

import javax.annotation.Nullable;

public final class Variable implements IdentifierIfc, ExpressionIfc {
    private final LocationIfc m_location;
    private final String m_name;

    public Variable( final LocationIfc location, final String name ) {
        m_location = location;
        m_name = name;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public String getName() {
        return m_name;
    }

    @Override public TypeIfc getType( final SymbolTable symbolTable ) {
        @Nullable final DeclarationIfc declaration = symbolTable.get( m_name );

        if ( declaration == null ) {
            throw new ASTException( m_location, "Variable '" + m_name + "' has not been assigned a type" );
        }

        return declaration.getType();
    }

    @Override public < T, R > R accept( final ExpressionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
