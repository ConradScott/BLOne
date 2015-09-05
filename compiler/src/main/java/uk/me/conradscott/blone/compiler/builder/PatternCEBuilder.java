package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.attributeconstraint.AttributeConstraintIfc;
import uk.me.conradscott.blone.ast.conditionelement.PatternCE;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class PatternCEBuilder {
    private PatternCEBuilder() {}

    static PatternCE build( final BLOneParser.PatternCEContext ctx, final ErrorCollectorIfc errorCollector ) {
        final PatternCE patternCE = new PatternCE( LocationBuilder.build( ctx ),
                                                   ctx.Identifier().getSymbol().getText() );

        for ( final BLOneParser.AttributeConstraintContext context : ctx.attributeConstraint() ) {
            final AttributeConstraintIfc attributeConstraint = AttributeConstraintBuilder.build( context );

            try {
                patternCE.put( attributeConstraint );
            } catch ( final ASTException e ) {
                errorCollector.error( attributeConstraint.getLocation(), e.getMessage() );
            }
        }

        return patternCE;
    }
}
