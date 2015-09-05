package uk.me.conradscott.blone.ast.attributeconstraint;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface AttributeConstraintVisitorIfc< T, R > {
    default List< R > visit( final Collection< ? extends AttributeConstraintIfc > constraints, final T t ) {
        return constraints.stream().map( constraint -> visit( constraint, t ) ).collect( Collectors.toList() );
    }

    default R visit( final AttributeConstraintIfc attributeConstraint, final T t ) {
        return attributeConstraint.accept( this, t );
    }

    R visit( SimpleAttributeConstraint attributeConstraint, T t );
    R visit( CapturedAttributeConstraint attributeConstraint, T t );
}
