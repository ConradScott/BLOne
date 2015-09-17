package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.action.AttributeExpr;
import uk.me.conradscott.blone.ast.action.TupleExpr;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class TupleExprBuilder {
    private TupleExprBuilder() {}

    static TupleExpr build( final BLOneParser.TupleExprContext ctx, final ErrorCollectorIfc errorCollector ) {
        final TupleExpr tupleExpr = new TupleExpr( LocationBuilder.build( ctx ),
                                                   ctx.Identifier().getSymbol().getText() );

        for ( final BLOneParser.AttributeExprContext context : ctx.attributeExpr() ) {
            final AttributeExpr attributeExpr = AttributeExprBuilder.build( context );

            try {
                tupleExpr.put( attributeExpr );
            } catch ( final ASTException e ) {
                errorCollector.error( attributeExpr.getLocation(), e.getMessage() );
            }
        }

        return tupleExpr;
    }
}
