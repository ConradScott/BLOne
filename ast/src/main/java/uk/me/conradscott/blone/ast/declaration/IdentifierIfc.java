package uk.me.conradscott.blone.ast.declaration;

import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface IdentifierIfc extends LocatedIfc {
    String getName();
}
