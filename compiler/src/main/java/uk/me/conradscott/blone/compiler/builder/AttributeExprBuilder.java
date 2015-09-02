package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.action.AttributeExpr;

final class AttributeExprBuilder {
    private AttributeExprBuilder() {}

    static AttributeExpr build( final BLOneParser.AttributeExprContext ctx ) {
        return new AttributeExpr( LocationBuilder.build( ctx ),
                                  ctx.Identifier().getSymbol().getText(),
                                  ExpressionBuilder.build( ctx.expression() ) );
    }
}
