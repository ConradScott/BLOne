package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.conditionelement.CapturedCE;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.DisjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.ExistentialCE;
import uk.me.conradscott.blone.ast.conditionelement.NegativeCE;
import uk.me.conradscott.blone.ast.conditionelement.UniversalCE;

import java.util.List;
import java.util.stream.Collectors;

final class ConditionElementBuilder {
    private ConditionElementBuilder() {}

    @NotNull static ConditionElementIfc build( @NotNull final BLOneParser.ConditionElementContext ctx ) {
        return ConditionElementVisitor.s_instance.visit( ctx );
    }

    private static final class ConditionElementVisitor extends BLOneParserBaseVisitor< ConditionElementIfc > {
        private static final ParseTreeVisitor< ConditionElementIfc > s_instance = new ConditionElementVisitor();

        @Override public ConditionElementIfc visitPatternCE( final BLOneParser.PatternCEContext ctx ) {
            return PatternCEBuilder.build( ctx );
        }

        @Override public ConditionElementIfc visitCapturedCE( final BLOneParser.CapturedCEContext ctx ) {
            return new CapturedCE( LocationBuilder.build( ctx ),
                                   VariableBuilder.build( ctx.Variable() ),
                                   build( ctx.conditionElement() ) );
        }

        @Override public ConditionElementIfc visitNotCE( final BLOneParser.NotCEContext ctx ) {
            return new NegativeCE( LocationBuilder.build( ctx ), build( ctx.conditionElement() ) );
        }

        @Override public ConditionElementIfc visitAndCE( final BLOneParser.AndCEContext ctx ) {
            final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( ConditionElementBuilder::build )
                                                             .collect( Collectors.toList() );

            return new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts );
        }

        @Override public ConditionElementIfc visitOrCE( final BLOneParser.OrCEContext ctx ) {
            final List< ConditionElementIfc > disjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( ConditionElementBuilder::build )
                                                             .collect( Collectors.toList() );

            return new DisjunctiveCE( LocationBuilder.build( ctx ), disjuncts );
        }

        @Override public ConditionElementIfc visitExistsCE( final BLOneParser.ExistsCEContext ctx ) {
            final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( ConditionElementBuilder::build )
                                                             .collect( Collectors.toList() );

            final ConjunctiveCE predicate = new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts );

            return new ExistentialCE( LocationBuilder.build( ctx ), predicate );
        }

        @Override public ConditionElementIfc visitForallCE( final BLOneParser.ForallCEContext ctx ) {
            final ConditionElementIfc range = build( ctx.rangeCE );

            final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( ConditionElementBuilder::build )
                                                             .collect( Collectors.toList() );

            final ConjunctiveCE predicate = new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts );

            return new UniversalCE( LocationBuilder.build( ctx ), range, predicate );
        }

        @Override
        protected ConditionElementIfc aggregateResult( final ConditionElementIfc aggregate,
                                                       final ConditionElementIfc nextResult )
        {
            assert aggregate == null;
            return nextResult;
        }
    }
}
