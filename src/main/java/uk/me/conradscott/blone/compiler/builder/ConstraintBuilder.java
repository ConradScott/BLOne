package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.constraint.CapturedConstraint;
import uk.me.conradscott.blone.ast.constraint.ConjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.constraint.DisjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.LiteralConstraint;
import uk.me.conradscott.blone.ast.constraint.NegativeConstraint;
import uk.me.conradscott.blone.ast.constraint.VariableConstraint;

import java.util.List;
import java.util.stream.Collectors;

final class ConstraintBuilder {
    private ConstraintBuilder() {}

    @NotNull static ConstraintIfc build( final BLOneParser.ConstraintContext ctx ) {
        return ConstraintVisitor.s_instance.visit( ctx );
    }

    private static final class ConstraintVisitor extends BLOneParserBaseVisitor< ConstraintIfc > {
        private static final ParseTreeVisitor< ConstraintIfc > s_instance = new ConstraintVisitor();

        @Override public ConstraintIfc visitLiteralConstraint( final BLOneParser.LiteralConstraintContext ctx ) {
            return new LiteralConstraint( LocationBuilder.build( ctx ), LiteralBuilder.build( ctx.literal() ) );
        }

        @Override public ConstraintIfc visitVariableConstraint( final BLOneParser.VariableConstraintContext ctx ) {
            return new VariableConstraint( LocationBuilder.build( ctx ), VariableBuilder.build( ctx.Variable() ) );
        }

        @Override public ConstraintIfc visitCapturedConstraint( final BLOneParser.CapturedConstraintContext ctx ) {
            return new CapturedConstraint( LocationBuilder.build( ctx ),
                                           VariableBuilder.build( ctx.Variable() ),
                                           build( ctx.constraint() ) );
        }

        @Override public ConstraintIfc visitNotConstraint( final BLOneParser.NotConstraintContext ctx ) {
            return new NegativeConstraint( LocationBuilder.build( ctx ), build( ctx.constraint() ) );
        }

        @Override public ConstraintIfc visitAndConstraint( final BLOneParser.AndConstraintContext ctx ) {
            final List< ConstraintIfc > conjuncts = ctx.constraint()
                                                       .stream()
                                                       .map( ConstraintBuilder::build )
                                                       .collect( Collectors.toList() );

            return new ConjunctiveConstraint( LocationBuilder.build( ctx ), conjuncts );
        }

        @Override public ConstraintIfc visitOrConstraint( final BLOneParser.OrConstraintContext ctx ) {
            final List< ConstraintIfc > disjuncts = ctx.constraint()
                                                       .stream()
                                                       .map( ConstraintBuilder::build )
                                                       .collect( Collectors.toList() );

            return new DisjunctiveConstraint( LocationBuilder.build( ctx ), disjuncts );
        }
    }
}
