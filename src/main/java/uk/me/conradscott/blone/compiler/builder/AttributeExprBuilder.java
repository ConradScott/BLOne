package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.action.AttributeExpr;

final class AttributeExprBuilder {
    private AttributeExprBuilder() {}

    static AttributeExpr build( final BLOneParser.AttributeExprContext ctx ) {
        final Token name = ctx.Identifier().getSymbol();

        return new AttributeExpr( LocationBuilder.build( ctx ),
                                  name.getText(),
                                  ExpressionBuilder.build( ctx.expression() ) );
    }
}
