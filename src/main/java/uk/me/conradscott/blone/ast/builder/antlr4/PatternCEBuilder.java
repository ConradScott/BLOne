package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.PatternCE;

final class PatternCEBuilder {
    private PatternCEBuilder() {}

    @NotNull static PatternCE build(final BLOneParser.PatternCEContext ctx) {
        final Token name = ctx.Identifier().getSymbol();

        return new PatternCE(Utils.location(ctx.getStart()), name.getText());
    }
}
