package uk.me.conradscott.blone.compiler.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.action.Assertion;
import uk.me.conradscott.blone.ast.action.Retraction;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.ast.scope.ActionTable;
import uk.me.conradscott.blone.ast.scope.RelationScope;
import uk.me.conradscott.blone.ast.scope.RuleScope;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

public final class ProgramBuilder {
    private static final Logger s_log = LogManager.getLogger( ProgramBuilder.class );

    private final ActionTable m_actions = new ActionTable();
    private final ScopeIfc< String, RelationDecl > m_relationScope = new RelationScope();
    private final ScopeIfc< String, RuleDecl > m_ruleScope = new RuleScope();
    private final ErrorCollectorIfc m_errorCollector;

    public ProgramBuilder( final ErrorCollectorIfc errorCollector ) {
        m_errorCollector = errorCollector;
    }

    public void build( final BLOneParser.ProgramContext ctx ) {
        new Visitor().visit( ctx );
    }

    public ActionTable getActions() {
        return m_actions;
    }

    public ScopeIfc< String, RelationDecl > getRelationScope() {
        return m_relationScope;
    }

    public ScopeIfc< String, RuleDecl > getRuleScope() {
        return m_ruleScope;
    }

    private final class Visitor extends BLOneParserBaseVisitor< Void > {
        @Override public Void visitRelationDecl( final BLOneParser.RelationDeclContext ctx ) {
            final RelationDecl decl = RelationDeclBuilder.build( ctx, m_errorCollector );

            try {
                m_relationScope.put( decl );
            } catch ( final ASTException e ) {
                m_errorCollector.error( decl.getLocation(), e.getMessage() );
            }

            return defaultResult();
        }

        @Override public Void visitRuleDecl( final BLOneParser.RuleDeclContext ctx ) {
            final RuleDecl decl = RuleDeclBuilder.build( ctx, m_errorCollector );

            try {
                m_ruleScope.put( decl );
            } catch ( final ASTException e ) {
                m_errorCollector.error( decl.getLocation(), e.getMessage() );
            }

            return defaultResult();
        }

        @Override public Void visitAssertion( final BLOneParser.AssertionContext ctx ) {
            m_actions.add( new Assertion( LocationBuilder.build( ctx ),
                                          RelationExprBuilder.build( ctx.relationExpr(), m_errorCollector ) ) );

            return defaultResult();
        }

        @Override public Void visitRetraction( final BLOneParser.RetractionContext ctx ) {
            m_actions.add( new Retraction( LocationBuilder.build( ctx ),
                                           PatternCEBuilder.build( ctx.patternCE(), m_errorCollector ) ) );

            return defaultResult();
        }
    }
}
