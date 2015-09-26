package uk.me.conradscott.blone.ast.literal;

import com.gs.collections.api.RichIterable;

public interface LiteralVisitorIfc< A, R > {
    default RichIterable< R > visit( final RichIterable< ? extends PrimitiveLiteralIfc< ? > > literals, final A a ) {
        return literals.collect( literal -> visit( literal, a ) );
    }

    default R visit( final PrimitiveLiteralIfc< ? > literal, final A a ) {
        return literal.accept( this, a );
    }

    R visit( BooleanLiteral literal, A a );
    R visit( CharacterLiteral literal, A a );
    R visit( DoubleFloatingPointLiteral literal, A a );
    R visit( FloatingPointLiteral literal, A a );
    R visit( IntegerLiteral literal, A a );
    R visit( LongIntegerLiteral literal, A a );
    R visit( StringLiteral literal, A a );
}
