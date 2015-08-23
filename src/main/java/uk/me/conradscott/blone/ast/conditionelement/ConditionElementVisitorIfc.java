package uk.me.conradscott.blone.ast.conditionelement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ConditionElementVisitorIfc< T, R > {
    @NotNull
    default List< R > visit( @NotNull final Collection< ? extends ConditionElementIfc > conditionElements,
                             @NotNull final T t )
    {
        return conditionElements.stream().map( ce -> visit( ce, t ) ).collect( Collectors.toList() );
    }

    @Nullable default R visit( @NotNull final ConditionElementIfc conditionElement, @NotNull final T t ) {
        return conditionElement.accept( this, t );
    }

    @Nullable R visit( @NotNull CapturedCE conditionElement, @NotNull T t );

    @Nullable R visit( @NotNull ConjunctiveCE conditionElement, @NotNull T t );

    @Nullable R visit( @NotNull DisjunctiveCE conditionElement, @NotNull T t );

    @Nullable R visit( @NotNull ExistentialCE conditionElement, @NotNull T t );

    @Nullable R visit( @NotNull NegativeCE conditionElement, @NotNull T t );

    @Nullable R visit( @NotNull PatternCE conditionElement, @NotNull T t );

    @Nullable R visit( @NotNull UniversalCE conditionElement, @NotNull T t );
}
