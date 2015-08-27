package uk.me.conradscott.blone.ast.conditionelement;

import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface ConditionElementIfc extends LocatedIfc {
    < T, R > R accept( ConditionElementVisitorIfc< T, R > visitor, T t );
}
