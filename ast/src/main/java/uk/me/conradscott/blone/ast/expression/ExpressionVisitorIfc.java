package uk.me.conradscott.blone.ast.expression;

import com.gs.collections.api.RichIterable;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;

public interface ExpressionVisitorIfc< T, R > {
    default RichIterable< R > visit( final RichIterable< ? extends ExpressionIfc > expressions, final T t ) {
        return expressions.collect( expression -> visit( expression, t ) );
    }

    default R visit( final ExpressionIfc expression, final T t ) {
        return expression.accept( this, t );
    }

    R visit( PrimitiveLiteralIfc< ? > literal, T t );
    R visit( Variable variable, T t );
}
