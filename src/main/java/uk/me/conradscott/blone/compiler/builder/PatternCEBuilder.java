package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.conditionelement.PatternCE;

final class PatternCEBuilder {
    private PatternCEBuilder() {}

    @NotNull static PatternCE build( final BLOneParser.PatternCEContext ctx ) {
        final Token name = ctx.Identifier().getSymbol();

        final PatternCE patternCE = new PatternCE( LocationBuilder.build( ctx ), name.getText() );

        for ( final BLOneParser.AttributeConstraintContext context : ctx.attributeConstraint() ) {
            assert context != null : "null AttributeConstraintContext in PatternCEContext";

            patternCE.put( AttributeConstraintBuilder.build( context ) );
        }

        return patternCE;
    }
}
