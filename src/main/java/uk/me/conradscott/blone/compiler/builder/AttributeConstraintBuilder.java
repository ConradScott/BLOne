package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.statement.AttributeConstraint;

final class AttributeConstraintBuilder {
    private AttributeConstraintBuilder() {}

    static AttributeConstraint build( final BLOneParser.AttributeConstraintContext ctx ) {
        final Token name = ctx.Identifier().getSymbol();

        return new AttributeConstraint( LocationBuilder.build( ctx ),
                                        name.getText(),
                                        ConstraintBuilder.build( ctx.constraint() ) );
    }
}
