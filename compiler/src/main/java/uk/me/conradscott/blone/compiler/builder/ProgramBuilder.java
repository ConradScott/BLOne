package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.action.Assertion;
import uk.me.conradscott.blone.ast.action.Retraction;
import uk.me.conradscott.blone.ast.scope.Program;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import javax.annotation.Nullable;

public final class ProgramBuilder {
    private ProgramBuilder() {}

    public static Program build( final ErrorCollectorIfc errorCollector, final BLOneParser.ProgramContext ctx )
    {
        return new Visitor( errorCollector ).visit( ctx );
    }

    private static final class Visitor extends BLOneParserBaseVisitor< Program > {
        private final ErrorCollectorIfc m_errorCollector;

        private Visitor( final ErrorCollectorIfc errorCollector ) {
            m_errorCollector = errorCollector;
        }

        @Override public Program visitRelationDecl( final BLOneParser.RelationDeclContext ctx ) {
            return new Program( RelationDeclBuilder.build( ctx, m_errorCollector ) );
        }

        @Override public Program visitRuleDecl( final BLOneParser.RuleDeclContext ctx ) {
            return new Program( RuleDeclBuilder.build( ctx, m_errorCollector ) );
        }

        @Override public Program visitAssertion( final BLOneParser.AssertionContext ctx ) {
            return new Program( new Assertion( LocationBuilder.build( ctx ),
                                               TupleExprBuilder.build( ctx.tupleExpr(), m_errorCollector ) ) );
        }

        @Override public Program visitRetraction( final BLOneParser.RetractionContext ctx ) {
            return new Program( new Retraction( LocationBuilder.build( ctx ),
                                                VariableBuilder.build( ctx.relationExpr().Variable() ) ) );
        }

        @Override protected Program defaultResult() {
            return Program.empty();
        }

        @Override protected Program aggregateResult( @Nullable final Program aggregate, final Program nextResult ) {
            assert aggregate != null;

            Program result = aggregate;

            try {
                result = result.put( nextResult );
            } catch ( final ASTException e ) {
                m_errorCollector.error( e.getLocation(), e.getMessage() );
            }

            return result;
        }
    }
}
