package uk.me.conradscott.blone.compiler.builder;

import com.gs.collections.api.list.ImmutableList;
import com.gs.collections.impl.factory.Lists;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.constraint.ConjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.constraint.DisjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ExpressionConstraint;
import uk.me.conradscott.blone.ast.constraint.NegativeConstraint;

final class ConstraintBuilder {
    private ConstraintBuilder() {}

    static ConstraintIfc build( final BLOneParser.ConstraintContext ctx ) {
        return Visitor.s_instance.visit( ctx );
    }

    private static final class Visitor extends BLOneParserBaseVisitor< ConstraintIfc > {
        private static final ParseTreeVisitor< ConstraintIfc > s_instance = new Visitor();

        @Override public ConstraintIfc visitExpressionConstraint( final BLOneParser.ExpressionConstraintContext ctx ) {
            return new ExpressionConstraint( LocationBuilder.build( ctx ),
                                             ExpressionBuilder.build( ctx.expression() ) );
        }

        @Override public ConstraintIfc visitNotConstraint( final BLOneParser.NotConstraintContext ctx ) {
            return new NegativeConstraint( LocationBuilder.build( ctx ), visit( ctx.constraint() ) );
        }

        @Override public ConstraintIfc visitAndConstraint( final BLOneParser.AndConstraintContext ctx ) {
            final ImmutableList< ConstraintIfc > conjuncts = Lists.immutable.withAll( ctx.constraint() )
                                                                            .collect( this::visit );

            return new ConjunctiveConstraint( LocationBuilder.build( ctx ), conjuncts );
        }

        @Override public ConstraintIfc visitOrConstraint( final BLOneParser.OrConstraintContext ctx ) {
            final ImmutableList< ConstraintIfc > disjuncts = Lists.immutable.withAll( ctx.constraint() )
                                                                            .collect( this::visit );

            return new DisjunctiveConstraint( LocationBuilder.build( ctx ), disjuncts );
        }
    }
}
