package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface ConstraintIfc extends LocatedIfc {
    @NotNull < T, R > R accept( @NotNull ConstraintVisitorIfc< T, R > visitor, @NotNull T t );
}
