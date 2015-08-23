package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ConstraintVisitorIfc< T, R > {
    @NotNull
    default List< R > visit( @NotNull final Collection< ? extends ConstraintIfc > constraints, @NotNull final T t )
    {
        return constraints.stream().map( constraint -> visit( constraint, t ) ).collect( Collectors.toList() );
    }

    @Nullable default R visit( @NotNull final ConstraintIfc constraint, @NotNull final T t ) {
        return constraint.accept( this, t );
    }

    @Nullable R visit( @NotNull CapturedConstraint constraint, @NotNull T t );

    @Nullable R visit( @NotNull ConjunctiveConstraint constraint, @NotNull T t );

    @Nullable R visit( @NotNull DisjunctiveConstraint constraint, @NotNull T t );

    @Nullable R visit( @NotNull LiteralConstraint constraint, @NotNull T t );

    @Nullable R visit( @NotNull NegativeConstraint constraint, @NotNull T t );

    @Nullable R visit( @NotNull VariableConstraint constraint, @NotNull T t );
}
