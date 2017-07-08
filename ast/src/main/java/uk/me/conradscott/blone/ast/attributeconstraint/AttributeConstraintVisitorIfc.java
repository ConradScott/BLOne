package uk.me.conradscott.blone.ast.attributeconstraint;

import org.eclipse.collections.api.RichIterable;

public interface AttributeConstraintVisitorIfc< T, R > {
    default RichIterable< R > visit( final RichIterable< ? extends AttributeConstraintIfc > constraints, final T t ) {
        return constraints.collect( constraint -> visit( constraint, t ) );
    }

    default R visit( final AttributeConstraintIfc attributeConstraint, final T t ) {
        return attributeConstraint.accept( this, t );
    }

    R visit( SimpleAttributeConstraint attributeConstraint, T t );
    R visit( CapturedAttributeConstraint attributeConstraint, T t );
}
