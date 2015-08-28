package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.action.AttributeExpr;
import uk.me.conradscott.blone.ast.action.RelationExpr;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class RelationExprBuilder {
    private RelationExprBuilder() {}

    static RelationExpr build( final BLOneParser.RelationExprContext ctx, final ErrorCollectorIfc errorCollector ) {
        final Token name = ctx.Identifier().getSymbol();

        final RelationExpr relationExpr = new RelationExpr( LocationBuilder.build( ctx ), name.getText() );

        for ( final BLOneParser.AttributeExprContext context : ctx.attributeExpr() ) {
            final AttributeExpr attributeExpr = AttributeExprBuilder.build( context );

            try {
                relationExpr.put( attributeExpr );
            } catch ( final ASTException e ) {
                errorCollector.error( attributeExpr.getLocation(), e.getMessage() );
            }
        }

        return relationExpr;
    }
}
