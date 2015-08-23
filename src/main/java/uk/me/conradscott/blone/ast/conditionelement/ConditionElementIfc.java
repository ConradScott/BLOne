package uk.me.conradscott.blone.ast.conditionelement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocatedIfc;

public interface ConditionElementIfc extends LocatedIfc {
    @Nullable < T, R > R accept( @NotNull ConditionElementVisitorIfc< T, R > visitor, @NotNull T t);
}
