package uk.me.conradscott.blone.compiler.builder;

import com.gs.collections.api.RichIterable;
import com.gs.collections.impl.factory.Lists;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class RuleDeclBuilder {
    private RuleDeclBuilder() {}

    static RuleDecl build( final BLOneParser.RuleDeclContext ctx, final ErrorCollectorIfc errorCollector ) {
        final RichIterable< ConditionElementIfc > conjuncts = Lists.immutable.withAll( ctx.conditionElement() )
                                                                             .collect( ce -> ConditionElementBuilder.build(
                                                                                     ce,
                                                                                     errorCollector ) );

        final RichIterable< ActionIfc > actions = Lists.immutable.withAll( ctx.action() )
                                                                 .collect( action -> ActionBuilder.build( action,
                                                                                                          errorCollector ) );

        return new RuleDecl( LocationBuilder.build( ctx ),
                             ctx.Identifier().getSymbol().getText(),
                             DocumentationStringBuilder.build( ctx.documentationString() ),
                             new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts ),
                             actions );
    }
}
