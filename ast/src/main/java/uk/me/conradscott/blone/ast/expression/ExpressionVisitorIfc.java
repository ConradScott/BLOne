package uk.me.conradscott.blone.ast.expression;

import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ExpressionVisitorIfc< T, R > {
    default List< R > visit( final Collection< ? extends ExpressionIfc > expressions, final T t ) {
        return expressions.stream().map( expression -> visit( expression, t ) ).collect( Collectors.toList() );
    }

    default R visit( final ExpressionIfc expression, final T t ) {
        return expression.accept( this, t );
    }

    R visit( PrimitiveLiteralIfc< ? > literal, T t );
    R visit( Variable variable, T t );
}
