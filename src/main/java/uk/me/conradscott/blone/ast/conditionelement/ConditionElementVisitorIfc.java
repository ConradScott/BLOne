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

    @Nullable R visitCapturedCE( @NotNull CapturedCE capturedCE, @NotNull T t );

    @Nullable R visitConjunctiveCE( @NotNull ConjunctiveCE conjunctiveCE, @NotNull T t );

    @Nullable R visitDisjunctiveCE( @NotNull DisjunctiveCE disjunctiveCE, @NotNull T t );

    @Nullable R visitExistentialCE( @NotNull ExistentialCE existentialCE, @NotNull T t );

    @Nullable R visitNegativeCE( @NotNull NegativeCE negativeCE, @NotNull T t );

    @Nullable R visitPatternCE( @NotNull PatternCE patternCE, @NotNull T t );

    @Nullable R visitUniversalCE( @NotNull UniversalCE universalCE, @NotNull T t );
}
