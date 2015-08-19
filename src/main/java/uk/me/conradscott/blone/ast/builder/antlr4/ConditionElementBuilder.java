package uk.me.conradscott.blone.ast.builder.antlr4;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.ConditionElementIfc;

public final class ConditionElementBuilder {
    private ConditionElementBuilder() {}

    @NotNull static ConditionElementIfc build(@NotNull final BLOneParser.ConditionElementContext ctx) {
        return null;
    }
}
