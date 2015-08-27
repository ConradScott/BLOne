package uk.me.conradscott.blone.ast.constraint;

import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface ConstraintIfc extends LocatedIfc {
    < T, R > R accept( ConstraintVisitorIfc< T, R > visitor, T t );
}
