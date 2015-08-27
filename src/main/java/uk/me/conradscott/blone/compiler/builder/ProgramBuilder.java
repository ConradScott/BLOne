package uk.me.conradscott.blone.compiler.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.scope.RelationScope;
import uk.me.conradscott.blone.ast.scope.RuleScope;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.statement.Assertion;
import uk.me.conradscott.blone.ast.statement.Retraction;
import uk.me.conradscott.blone.ast.statement.RuleDecl;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollector;

public final class ProgramBuilder {
    @NotNull private static final Logger s_log = LogManager.getLogger( ProgramBuilder.class );

    @NotNull private final ScopeIfc< String, RelationDecl > m_relationScope = new RelationScope();
    @NotNull private final ScopeIfc< String, RuleDecl > m_ruleScope = new RuleScope();
    private final ErrorCollector m_errorCollector;  // TODO: Pass down

    public ProgramBuilder( final ErrorCollector errorCollector ) {
        m_errorCollector = errorCollector;
    }

    public void build( @NotNull final BLOneParser.ProgramContext ctx ) {
        new ProgramVisitor().visit( ctx );
    }

    @NotNull public ScopeIfc< String, RelationDecl > getRelationScope() {
        return m_relationScope;
    }

    @NotNull public ScopeIfc< String, RuleDecl > getRuleScope() {
        return m_ruleScope;
    }

    private final class ProgramVisitor extends BLOneParserBaseVisitor< Void > {
        @Override public Void visitRelationDecl( final BLOneParser.RelationDeclContext ctx ) {
            final RelationDecl decl = RelationDeclBuilder.build( ctx );
            m_relationScope.put( decl );
            return defaultResult();
        }

        @Override public Void visitRuleDecl( final BLOneParser.RuleDeclContext ctx ) {
            final RuleDecl decl = RuleDeclBuilder.build( ctx );
            m_ruleScope.put( decl );
            return defaultResult();
        }

        @Override public Void visitAssertion( final BLOneParser.AssertionContext ctx ) {
            final Assertion assertion = new Assertion( LocationBuilder.build( ctx ),
                                                       RelationExprBuilder.build( ctx.relationExpr() ) );
            return defaultResult();
        }

        @Override public Void visitRetraction( final BLOneParser.RetractionContext ctx ) {
            final Retraction retraction = new Retraction( LocationBuilder.build( ctx ),
                                                          PatternCEBuilder.build( ctx.patternCE() ) );
            return defaultResult();
        }
    }
}
