package uk.me.conradscott.blone.ast.builder.antlr4;

import com.google.common.collect.Lists;
import org.antlr.v4.runtime.tree.TerminalNode;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.AssignedPatternCE;
import uk.me.conradscott.blone.ast.ConditionElementIfc;
import uk.me.conradscott.blone.ast.ConjunctionCE;
import uk.me.conradscott.blone.ast.DisjunctionCE;
import uk.me.conradscott.blone.ast.ExistentialCE;
import uk.me.conradscott.blone.ast.NegationCE;
import uk.me.conradscott.blone.ast.UniversalCE;
import uk.me.conradscott.blone.ast.Variable;

import java.util.List;

public class ConditionElementVisitor extends BLOneParserBaseVisitor< ConditionElementIfc > {
    static final ConditionElementVisitor INSTANCE = new ConditionElementVisitor();

    @Override public ConditionElementIfc visitConditionElement( final BLOneParser.ConditionElementContext ctx ) {
        assert ctx.getChildCount() == 1;
        return ctx.getChild( 0 ).accept( this );
    }

    @Override public ConditionElementIfc visitPatternCE( final BLOneParser.PatternCEContext ctx ) {
        return PatternCEBuilder.build( ctx );
    }

    @Override public ConditionElementIfc visitAssignedPatternCE( final BLOneParser.AssignedPatternCEContext ctx ) {
        final TerminalNode node = ctx.Variable();
        final Variable variable = new Variable( Locations.build( node.getSymbol() ), node.getText() );

        final ConditionElementIfc conditionElementIfc = ctx.conditionElement().accept( this );

        return new AssignedPatternCE( Locations.build( ctx ), variable, conditionElementIfc );
    }

    @Override public ConditionElementIfc visitNotCE( final BLOneParser.NotCEContext ctx ) {
        final ConditionElementIfc conditionElementIfc = ctx.conditionElement().accept( this );

        return new NegationCE( Locations.build( ctx ), conditionElementIfc );
    }

    @Override public ConditionElementIfc visitAndCE( final BLOneParser.AndCEContext ctx ) {
        final List< ConditionElementIfc > conjuncts = Lists.newArrayListWithCapacity( ctx.conditionElement().size() );

        for ( final BLOneParser.ConditionElementContext context : ctx.conditionElement() ) {
            conjuncts.add( context.accept( this ) );
        }

        return new ConjunctionCE( Locations.build( ctx ), conjuncts );
    }

    @Override public ConditionElementIfc visitOrCE( final BLOneParser.OrCEContext ctx ) {
        final List< ConditionElementIfc > disjuncts = Lists.newArrayListWithCapacity( ctx.conditionElement().size() );

        for ( final BLOneParser.ConditionElementContext context : ctx.conditionElement() ) {
            disjuncts.add( context.accept( this ) );
        }

        return new DisjunctionCE( Locations.build( ctx ), disjuncts );
    }

    @Override public ConditionElementIfc visitExistsCE( final BLOneParser.ExistsCEContext ctx ) {
        final List< ConditionElementIfc > conjuncts = Lists.newArrayListWithCapacity( ctx.conditionElement().size() );

        for ( final BLOneParser.ConditionElementContext context : ctx.conditionElement() ) {
            conjuncts.add( context.accept( this ) );
        }

        final ConjunctionCE predicate = new ConjunctionCE( Locations.build( ctx ), conjuncts );

        return new ExistentialCE( Locations.build( ctx ), predicate );
    }

    @Override public ConditionElementIfc visitForallCE( final BLOneParser.ForallCEContext ctx ) {
        final ConditionElementIfc range = ctx.rangeCE.accept( this );

        final List< ConditionElementIfc > conjuncts = Lists.newArrayListWithCapacity( ctx.predicateCEs.size() );

        for ( final BLOneParser.ConditionElementContext context : ctx.predicateCEs ) {
            conjuncts.add( context.accept( this ) );
        }

        final ConjunctionCE predicate = new ConjunctionCE( Locations.build( ctx ), conjuncts );

        return new UniversalCE( Locations.build( ctx ), range, predicate );
    }

    @Override
    protected ConditionElementIfc aggregateResult( final ConditionElementIfc aggregate,
                                                   final ConditionElementIfc nextResult )
    {
        assert aggregate == null;
        return nextResult;
    }
}
