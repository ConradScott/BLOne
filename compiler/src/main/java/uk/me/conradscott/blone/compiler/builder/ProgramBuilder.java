package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.action.Assertion;
import uk.me.conradscott.blone.ast.action.Retraction;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.ast.scope.Program;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

public final class ProgramBuilder {
    private ProgramBuilder() {}

    public static Program build( final ErrorCollectorIfc errorCollector,
                                 final Program program,
                                 final BLOneParser.ProgramContext ctx )
    {
        new Visitor( program, errorCollector ).visit( ctx );
        return program;
    }

    private static final class Visitor extends BLOneParserBaseVisitor< Void > {
        private final Program m_program;
        private final ErrorCollectorIfc m_errorCollector;

        private Visitor( final Program program, final ErrorCollectorIfc errorCollector ) {
            m_program = program;
            m_errorCollector = errorCollector;
        }

        @Override public Void visitRelationDecl( final BLOneParser.RelationDeclContext ctx ) {
            final RelationDecl decl = RelationDeclBuilder.build( ctx, m_errorCollector );

            try {
                m_program.put( decl );
            } catch ( final ASTException e ) {
                m_errorCollector.error( decl.getLocation(), e.getMessage() );
            }

            return defaultResult();
        }

        @Override public Void visitRuleDecl( final BLOneParser.RuleDeclContext ctx ) {
            final RuleDecl decl = RuleDeclBuilder.build( ctx, m_errorCollector );

            try {
                m_program.put( decl );
            } catch ( final ASTException e ) {
                m_errorCollector.error( decl.getLocation(), e.getMessage() );
            }

            return defaultResult();
        }

        @Override public Void visitAssertion( final BLOneParser.AssertionContext ctx ) {
            m_program.add( new Assertion( LocationBuilder.build( ctx ),
                                          RelationExprBuilder.build( ctx.relationExpr(), m_errorCollector ) ) );

            return defaultResult();
        }

        @Override public Void visitRetraction( final BLOneParser.RetractionContext ctx ) {
            m_program.add( new Retraction( LocationBuilder.build( ctx ),
                                           PatternCEBuilder.build( ctx.patternCE(), m_errorCollector ) ) );

            return defaultResult();
        }
    }
}
