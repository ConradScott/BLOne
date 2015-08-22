package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.RelationExpr;

final class RelationExprBuilder {
    private RelationExprBuilder() {}

    @NotNull static RelationExpr build(final BLOneParser.RelationExprContext ctx) {
        final Token name = ctx.Identifier().getSymbol();

        return new RelationExpr(Locations.build( ctx.getStart() ), name.getText());
    }
}
