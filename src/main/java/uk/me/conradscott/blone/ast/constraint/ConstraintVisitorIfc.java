package uk.me.conradscott.blone.ast.constraint;

import org.jetbrains.annotations.NotNull;

public interface ConstraintVisitorIfc< T, R > {
    @NotNull R visitCapturedConstraint( @NotNull CapturedConstraint capturedConstraint, @NotNull T t );

    @NotNull R visitConjunctiveConstraint( @NotNull ConjunctiveConstraint conjunctiveConstraint, @NotNull T t );

    @NotNull R visitDisjunctiveConstraint( @NotNull DisjunctiveConstraint disjunctiveConstraint, @NotNull T t );

    @NotNull R visitLiteralConstraint( @NotNull LiteralConstraint literalConstraint, @NotNull T t );

    @NotNull R visitNegativeConstraint( @NotNull NegativeConstraint negativeConstraint, @NotNull T t );

    @NotNull R visitVariableConstraint( @NotNull VariableConstraint variableConstraint, @NotNull T t );
}
