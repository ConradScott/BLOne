package uk.me.conradscott.blone.ast.conditionelement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ConditionElementVisitorIfc< T, R > {
    default List< R > visit( final Collection< ? extends ConditionElementIfc > conditionElements, final T t ) {
        return conditionElements.stream().map( ce -> visit( ce, t ) ).collect( Collectors.toList() );
    }

    default R visit( final ConditionElementIfc conditionElement, final T t ) {
        return conditionElement.accept( this, t );
    }

    R visit( CapturedCE conditionElement, T t );
    R visit( PatternCE conditionElement, T t );
    R visit( NegativeCE conditionElement, T t );
    R visit( ConjunctiveCE conditionElement, T t );
    R visit( DisjunctiveCE conditionElement, T t );
    R visit( ExistentialCE conditionElement, T t );
    R visit( UniversalCE conditionElement, T t );
}
