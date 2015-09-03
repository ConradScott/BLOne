package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.constraint.CapturedConstraint;
import uk.me.conradscott.blone.ast.constraint.ConjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.constraint.DisjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ExpressionConstraint;
import uk.me.conradscott.blone.ast.constraint.NegativeConstraint;

import java.util.List;
import java.util.stream.Collectors;

final class ConstraintBuilder {
    private ConstraintBuilder() {}

    static ConstraintIfc build( final BLOneParser.ConstraintContext ctx ) {
        return ConstraintVisitor.s_instance.visit( ctx );
    }

    private static final class ConstraintVisitor extends BLOneParserBaseVisitor< ConstraintIfc > {
        private static final ParseTreeVisitor< ConstraintIfc > s_instance = new ConstraintVisitor();

        @Override public ConstraintIfc visitExpressionConstraint( final BLOneParser.ExpressionConstraintContext ctx ) {
            return new ExpressionConstraint( LocationBuilder.build( ctx ),
                                             ExpressionBuilder.build( ctx.expression() ) );
        }

        @Override public ConstraintIfc visitCapturedConstraint( final BLOneParser.CapturedConstraintContext ctx ) {
            return new CapturedConstraint( LocationBuilder.build( ctx ),
                                           VariableBuilder.build( ctx.Variable() ),
                                           visit( ctx.constraint() ) );
        }

        @Override public ConstraintIfc visitNotConstraint( final BLOneParser.NotConstraintContext ctx ) {
            return new NegativeConstraint( LocationBuilder.build( ctx ), visit( ctx.constraint() ) );
        }

        @Override public ConstraintIfc visitAndConstraint( final BLOneParser.AndConstraintContext ctx ) {
            final List< ConstraintIfc > conjuncts = ctx.constraint()
                                                       .stream()
                                                       .map( this::visit )
                                                       .collect( Collectors.toList() );

            return new ConjunctiveConstraint( LocationBuilder.build( ctx ), conjuncts );
        }

        @Override public ConstraintIfc visitOrConstraint( final BLOneParser.OrConstraintContext ctx ) {
            final List< ConstraintIfc > disjuncts = ctx.constraint()
                                                       .stream()
                                                       .map( this::visit )
                                                       .collect( Collectors.toList() );

            return new DisjunctiveConstraint( LocationBuilder.build( ctx ), disjuncts );
        }
    }
}
