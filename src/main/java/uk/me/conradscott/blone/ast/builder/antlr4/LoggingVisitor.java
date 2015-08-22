package uk.me.conradscott.blone.ast.builder.antlr4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.Assertion;
import uk.me.conradscott.blone.ast.AttributeDecl;
import uk.me.conradscott.blone.ast.RelationDecl;
import uk.me.conradscott.blone.ast.RelationScope;
import uk.me.conradscott.blone.ast.Retraction;
import uk.me.conradscott.blone.ast.RuleDecl;
import uk.me.conradscott.blone.ast.RuleScope;
import uk.me.conradscott.blone.ast.ScopeIfc;

public final class LoggingVisitor extends BLOneParserBaseVisitor<LoggingVisitor> {
    private static final Logger LOGGER = LogManager.getLogger(LoggingVisitor.class);

    private final ScopeIfc<String, RelationDecl> m_relationScope = new RelationScope();
    private final ScopeIfc<String, RuleDecl> m_ruleScope = new RuleScope();

    @Override public LoggingVisitor visitProgram(final BLOneParser.ProgramContext ctx) {
        super.visitProgram(ctx);

        for (final RelationDecl relationDecl : m_relationScope) {
            LOGGER.info(relationDecl.getName());

            if (relationDecl.getDocumentationString() != null) {
                LOGGER.info(relationDecl.getDocumentationString());
            }

            for (final AttributeDecl attributeDecl : relationDecl) {
                LOGGER.info("\t(" + attributeDecl.getName() + ' ' + attributeDecl.getType().getName() + ')');
            }
        }

        for (final RuleDecl ruleDecl : m_ruleScope) {
            LOGGER.info(ruleDecl.getName());
        }

        return this;
    }

    @Override public LoggingVisitor visitRelationDecl(final BLOneParser.RelationDeclContext ctx) {
        final RelationDecl decl = RelationDeclBuilder.build(ctx);
        m_relationScope.put(decl);
        return this;
    }

    @Override public LoggingVisitor visitRuleDecl(final BLOneParser.RuleDeclContext ctx) {
        final RuleDecl decl = RuleDeclBuilder.build(ctx);
        m_ruleScope.put(decl);
        return this;
    }

    @Override public LoggingVisitor visitAssertion(final BLOneParser.AssertionContext ctx) {
        final Assertion assertion = new Assertion(Locations.build( ctx.getStart() ),
                                                  RelationExprBuilder.build(ctx.relationExpr()));
        return this;
    }

    @Override public LoggingVisitor visitRetraction(final BLOneParser.RetractionContext ctx) {
        final Retraction retraction = new Retraction(Locations.build( ctx.getStart() ),
                                                     PatternCEBuilder.build(ctx.patternCE()));
        return this;
    }
}
