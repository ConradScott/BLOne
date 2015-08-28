package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.expression.ExpressionIfc;

final class ExpressionBuilder {
    private ExpressionBuilder() {}

    static ExpressionIfc build( final BLOneParser.ExpressionContext ctx ) {
        return ExpressionVisitor.s_instance.visit( ctx );
    }

    private static final class ExpressionVisitor extends BLOneParserBaseVisitor< ExpressionIfc > {
        private static final ParseTreeVisitor< ExpressionIfc > s_instance = new ExpressionVisitor();

        @Override public ExpressionIfc visitLiteralExpression( final BLOneParser.LiteralExpressionContext ctx ) {
            return LiteralBuilder.build( ctx.literal() );
        }

        @Override public ExpressionIfc visitVariableExpression( final BLOneParser.VariableExpressionContext ctx ) {
            return VariableBuilder.build( ctx.Variable() );
        }
    }
}
