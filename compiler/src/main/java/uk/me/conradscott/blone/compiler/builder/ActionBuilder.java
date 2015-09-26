package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.action.Assertion;
import uk.me.conradscott.blone.ast.action.AttributeExpr;
import uk.me.conradscott.blone.ast.action.Modification;
import uk.me.conradscott.blone.ast.action.Println;
import uk.me.conradscott.blone.ast.action.Retraction;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class ActionBuilder {
    private ActionBuilder() {}

    static ActionIfc build( final BLOneParser.ActionContext ctx, final ErrorCollectorIfc errorCollector ) {
        return new Visitor( errorCollector ).visit( ctx );
    }

    private static final class Visitor extends BLOneParserBaseVisitor< ActionIfc > {
        private final ErrorCollectorIfc m_errorCollector;

        private Visitor( final ErrorCollectorIfc errorCollector ) {
            m_errorCollector = errorCollector;
        }

        @Override public ActionIfc visitAssertion( final BLOneParser.AssertionContext ctx ) {
            return new Assertion( LocationBuilder.build( ctx ),
                                  TupleExprBuilder.build( ctx.tupleExpr(), m_errorCollector ) );
        }

        @Override public ActionIfc visitRetraction( final BLOneParser.RetractionContext ctx ) {
            return new Retraction( LocationBuilder.build( ctx ),
                                   VariableBuilder.build( ctx.relationExpr().Variable() ) );
        }

        @Override public ActionIfc visitModification( final BLOneParser.ModificationContext ctx ) {
            Modification result = new Modification( LocationBuilder.build( ctx ),
                                                    VariableBuilder.build( ctx.relationExpr().Variable() ) );

            for ( final BLOneParser.AttributeExprContext context : ctx.attributeExpr() ) {
                final AttributeExpr attributeExpr = AttributeExprBuilder.build( context );

                try {
                    result = result.put( attributeExpr );
                } catch ( final ASTException e ) {
                    m_errorCollector.error( attributeExpr.getLocation(), e.getMessage() );
                }
            }

            return result;
        }

        @Override public ActionIfc visitPrintln( final BLOneParser.PrintlnContext ctx ) {
            return new Println( LocationBuilder.build( ctx ), ExpressionBuilder.build( ctx.expression() ) );
        }
    }
}
