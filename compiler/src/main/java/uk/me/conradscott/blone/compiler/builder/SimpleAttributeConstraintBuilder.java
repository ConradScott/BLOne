package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.attributeconstraint.SimpleAttributeConstraint;

final class SimpleAttributeConstraintBuilder {
    private SimpleAttributeConstraintBuilder() {}

    static SimpleAttributeConstraint build( final BLOneParser.SimpleAttributeConstraintContext ctx ) {
        return new SimpleAttributeConstraint( LocationBuilder.build( ctx ),
                                              ctx.Identifier().getSymbol().getText(),
                                              ConstraintBuilder.build( ctx.constraint() ) );
    }
}
