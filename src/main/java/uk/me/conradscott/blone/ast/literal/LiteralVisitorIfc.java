package uk.me.conradscott.blone.ast.literal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface LiteralVisitorIfc< A, R > {
    @NotNull
    default List< R > visit( @NotNull final Collection< ? extends PrimitiveLiteralIfc< ? > > literals,
                             @NotNull final A a )
    {
        return literals.stream().map( literal -> visit( literal, a ) ).collect( Collectors.toList() );
    }

    @Nullable default R visit( @NotNull final PrimitiveLiteralIfc< ? > literal, @NotNull final A a ) {
        return literal.accept( this, a );
    }

    @Nullable R visit( @NotNull BooleanLiteral literal, @NotNull A a );

    @Nullable R visit( @NotNull CharacterLiteral literal, @NotNull A a );

    @Nullable R visit( @NotNull DoubleFloatingPointLiteral literal, @NotNull A a );

    @Nullable R visit( @NotNull FloatingPointLiteral literal, @NotNull A a );

    @Nullable R visit( @NotNull IntegerLiteral literal, @NotNull A a );

    @Nullable R visit( @NotNull LongIntegerLiteral literal, @NotNull A a );

    @Nullable R visit( @NotNull StringLiteral literal, @NotNull A a );
}
