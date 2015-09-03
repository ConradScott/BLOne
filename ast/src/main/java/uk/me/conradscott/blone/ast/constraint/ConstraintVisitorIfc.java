package uk.me.conradscott.blone.ast.constraint;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ConstraintVisitorIfc< T, R > {
    default List< R > visit( final Collection< ? extends ConstraintIfc > constraints, final T t ) {
        return constraints.stream().map( constraint -> visit( constraint, t ) ).collect( Collectors.toList() );
    }

    default R visit( final ConstraintIfc constraint, final T t ) {
        return constraint.accept( this, t );
    }

    R visit( CapturedConstraint constraint, T t );
    R visit( ConjunctiveConstraint constraint, T t );
    R visit( DisjunctiveConstraint constraint, T t );
    R visit( ExpressionConstraint constraint, T t );
    R visit( NegativeConstraint constraint, T t );
}
