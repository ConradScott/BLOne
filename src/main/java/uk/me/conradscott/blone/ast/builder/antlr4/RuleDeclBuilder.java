package uk.me.conradscott.blone.ast.builder.antlr4;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.ConditionElementIfc;
import uk.me.conradscott.blone.ast.ConjunctionCE;
import uk.me.conradscott.blone.ast.RuleDecl;

import java.util.List;

public final class RuleDeclBuilder {
    private RuleDeclBuilder() {}

    @NotNull static RuleDecl build( @NotNull final BLOneParser.RuleDeclContext ctx ) {
        final List< BLOneParser.ConditionElementContext > conditionElementContexts = ctx.conditionElement();

        final List< ConditionElementIfc > conjuncts = Lists.newArrayListWithCapacity( conditionElementContexts.size() );

        for ( final BLOneParser.ConditionElementContext conditionElementContext : conditionElementContexts ) {
            conjuncts.add( ConditionElementBuilder.build( conditionElementContext ) );
        }

        final ConditionElementIfc conditionElement = new ConjunctionCE( Locations.build( ctx ), conjuncts );

        return new RuleDecl( Locations.build( ctx ),
                             ctx.Identifier().getSymbol().getText(),
                             DocumentationStringBuilder.build( ctx.documentationString() ),
                             conditionElement );
    }
}
