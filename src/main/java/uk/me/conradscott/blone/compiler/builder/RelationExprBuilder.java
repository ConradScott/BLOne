package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.statement.RelationExpr;

final class RelationExprBuilder {
    private RelationExprBuilder() {}

    static RelationExpr build( final BLOneParser.RelationExprContext ctx ) {
        final Token name = ctx.Identifier().getSymbol();

        return new RelationExpr( LocationBuilder.build( ctx ), name.getText() );
    }
}
