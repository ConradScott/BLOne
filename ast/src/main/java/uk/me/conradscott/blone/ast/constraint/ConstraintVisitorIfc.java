package uk.me.conradscott.blone.ast.constraint;

import org.eclipse.collections.api.RichIterable;

public interface ConstraintVisitorIfc< T, R > {
    default RichIterable< R > visit( final RichIterable< ? extends ConstraintIfc > constraints, final T t ) {
        return constraints.collect( constraint -> visit( constraint, t ) );
    }

    default R visit( final ConstraintIfc constraint, final T t ) {
        return constraint.accept( this, t );
    }

    R visit( ExpressionConstraint constraint, T t );
    R visit( NegativeConstraint constraint, T t );
    R visit( ConjunctiveConstraint constraint, T t );
    R visit( DisjunctiveConstraint constraint, T t );
}
