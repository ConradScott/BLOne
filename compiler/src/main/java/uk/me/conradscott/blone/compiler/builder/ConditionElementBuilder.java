package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.conditionelement.CapturedCE;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.DisjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.ExistentialCE;
import uk.me.conradscott.blone.ast.conditionelement.NegativeCE;
import uk.me.conradscott.blone.ast.conditionelement.UniversalCE;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

final class ConditionElementBuilder {
    private ConditionElementBuilder() {}

    static ConditionElementIfc build( final BLOneParser.ConditionElementContext ctx,
                                      final ErrorCollectorIfc errorCollector )
    {
        return new Visitor( errorCollector ).visit( ctx );
    }

    private static final class Visitor extends BLOneParserBaseVisitor< ConditionElementIfc > {
        private final ErrorCollectorIfc m_errorCollector;

        private Visitor( final ErrorCollectorIfc errorCollector ) {
            m_errorCollector = errorCollector;
        }

        @Override public ConditionElementIfc visitPatternCE( final BLOneParser.PatternCEContext ctx ) {
            return PatternCEBuilder.build( ctx, m_errorCollector );
        }

        @Override public ConditionElementIfc visitCapturedCE( final BLOneParser.CapturedCEContext ctx ) {
            return new CapturedCE( LocationBuilder.build( ctx ),
                                   VariableBuilder.build( ctx.Variable() ),
                                   build( ctx.conditionElement(), m_errorCollector ) );
        }

        @Override public ConditionElementIfc visitNotCE( final BLOneParser.NotCEContext ctx ) {
            return new NegativeCE( LocationBuilder.build( ctx ), build( ctx.conditionElement(), m_errorCollector ) );
        }

        @Override public ConditionElementIfc visitAndCE( final BLOneParser.AndCEContext ctx ) {
            final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( this::visit )
                                                             .collect( Collectors.toList() );

            return new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts );
        }

        @Override public ConditionElementIfc visitOrCE( final BLOneParser.OrCEContext ctx ) {
            final List< ConditionElementIfc > disjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( this::visit )
                                                             .collect( Collectors.toList() );

            return new DisjunctiveCE( LocationBuilder.build( ctx ), disjuncts );
        }

        @Override public ConditionElementIfc visitExistsCE( final BLOneParser.ExistsCEContext ctx ) {
            final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( this::visit )
                                                             .collect( Collectors.toList() );

            final ConjunctiveCE predicate = new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts );

            return new ExistentialCE( LocationBuilder.build( ctx ), predicate );
        }

        @Override public ConditionElementIfc visitForallCE( final BLOneParser.ForallCEContext ctx ) {
            final ConditionElementIfc range = build( ctx.rangeCE, m_errorCollector );

            final List< ConditionElementIfc > conjuncts = ctx.conditionElement()
                                                             .stream()
                                                             .map( this::visit )
                                                             .collect( Collectors.toList() );

            final ConjunctiveCE predicate = new ConjunctiveCE( LocationBuilder.build( ctx ), conjuncts );

            return new UniversalCE( LocationBuilder.build( ctx ), range, predicate );
        }

        @Override
        protected ConditionElementIfc aggregateResult( @Nullable final ConditionElementIfc aggregate,
                                                       final ConditionElementIfc nextResult )
        {
            assert aggregate == null;
            return nextResult;
        }
    }
}