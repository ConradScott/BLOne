package uk.me.conradscott.blone.ast.declaration;

import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.type.TypeIfc;

public interface DeclarationIfc extends LocatedIfc {
    IdentifierIfc getIdentifier();
    String getName();
    TypeIfc getType();
}
