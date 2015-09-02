package uk.me.conradscott.blone.ast.action;

import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface ActionIfc extends LocatedIfc {
    < T, R > R accept( ActionVisitorIfc< T, R > visitor, T t );
}
