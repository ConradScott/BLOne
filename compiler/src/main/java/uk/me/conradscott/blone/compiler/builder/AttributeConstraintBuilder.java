package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.constraint.AttributeConstraint;

final class AttributeConstraintBuilder {
    private AttributeConstraintBuilder() {}

    static AttributeConstraint build( final BLOneParser.AttributeConstraintContext ctx ) {
        return new AttributeConstraint( LocationBuilder.build( ctx ),
                                        ctx.Identifier().getSymbol().getText(),
                                        ConstraintBuilder.build( ctx.constraint() ) );
    }
}
