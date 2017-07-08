package uk.me.conradscott.blone.ast.conditionelement;

import org.eclipse.collections.api.RichIterable;

public interface ConditionElementVisitorIfc< T, R > {
    default RichIterable< R > visit( final RichIterable< ? extends ConditionElementIfc > ces, final T t )
    {
        return ces.collect( ce -> visit( ce, t ) );
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
