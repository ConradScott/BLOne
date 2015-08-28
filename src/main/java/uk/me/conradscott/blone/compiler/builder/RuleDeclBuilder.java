package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import java.util.List;
import java.util.stream.Collectors;

final class RuleDeclBuilder {
    private RuleDeclBuilder() {}

    static RuleDecl build( final BLOneParser.RuleDeclContext ctx, final ErrorCollectorIfc errorCollector ) {
        final Token name = ctx.Identifier().getSymbol();

        final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                         .stream()
                                                         .map( ce -> ConditionElementBuilder.build( ce,
                                                                                                    errorCollector ) )
                                                         .collect( Collectors.toList() );

        final List< ActionIfc > actions = ctx.action()
                                             .stream()
                                             .map( action -> ActionBuilder.build( action, errorCollector ) )
                                             .collect( Collectors.toList() );

        return new RuleDecl( LocationBuilder.build( ctx ),
                             name.getText(),
                             DocumentationStringBuilder.build( ctx.documentationString() ),
                             new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts ),
                             actions );
    }
}
