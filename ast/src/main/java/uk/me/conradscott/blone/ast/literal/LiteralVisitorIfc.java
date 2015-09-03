package uk.me.conradscott.blone.ast.literal;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface LiteralVisitorIfc< A, R > {
    default List< R > visit( final Collection< ? extends PrimitiveLiteralIfc< ? > > literals, final A a ) {
        return literals.stream().map( literal -> visit( literal, a ) ).collect( Collectors.toList() );
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
