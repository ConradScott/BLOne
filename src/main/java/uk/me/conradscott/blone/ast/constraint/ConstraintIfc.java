package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface ConstraintIfc extends LocatedIfc {
    @Nullable < T, R > R accept( @NotNull ConstraintVisitorIfc< T, R > visitor, @NotNull T t );
}
