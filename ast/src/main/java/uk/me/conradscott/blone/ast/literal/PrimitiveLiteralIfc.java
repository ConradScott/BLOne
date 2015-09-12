package uk.me.conradscott.blone.ast.literal;

import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.expression.ExpressionIfc;
import uk.me.conradscott.blone.ast.expression.ExpressionVisitorIfc;
import uk.me.conradscott.blone.ast.type.TypeIfc;

public interface PrimitiveLiteralIfc< P > extends ExpressionIfc {
    P getValue();

    @Override default TypeIfc getType( final SymbolTable symbolTable ) {
        return getType();
    }

    TypeIfc getType();

    < A, R > R accept( LiteralVisitorIfc< A, R > visitor, A a );

    @Override default < T, R > R accept( final ExpressionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
