package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.statement.RuleDecl;

import java.util.List;
import java.util.stream.Collectors;

final class RuleDeclBuilder {
    private RuleDeclBuilder() {}

    @NotNull static RuleDecl build( @NotNull final BLOneParser.RuleDeclContext ctx ) {
        final Token name = ctx.Identifier().getSymbol();

        final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                         .stream()
                                                         .map( ConditionElementBuilder::build )
                                                         .collect( Collectors.toList() );

        return new RuleDecl( LocationBuilder.build( ctx ),
                             name.getText(),
                             DocumentationStringBuilder.build( ctx.documentationString() ),
                             new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts ) );
    }
}
