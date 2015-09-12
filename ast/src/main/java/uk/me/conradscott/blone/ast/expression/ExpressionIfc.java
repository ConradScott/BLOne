package uk.me.conradscott.blone.ast.expression;

import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.type.TypeIfc;

public interface ExpressionIfc extends LocatedIfc {
    TypeIfc getType( SymbolTable symbolTable );

    < T, R > R accept( ExpressionVisitorIfc< T, R > visitor, T t );
}
