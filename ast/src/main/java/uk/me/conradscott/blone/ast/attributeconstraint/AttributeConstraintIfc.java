package uk.me.conradscott.blone.ast.attributeconstraint;

import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface AttributeConstraintIfc extends LocatedIfc {
    String getName();

    < T, R > R accept( AttributeConstraintVisitorIfc< T, R > visitor, T t );
}
