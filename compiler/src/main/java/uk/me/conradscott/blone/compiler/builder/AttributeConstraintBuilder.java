package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.attributeconstraint.AttributeConstraintIfc;
import uk.me.conradscott.blone.ast.attributeconstraint.CapturedAttributeConstraint;
import uk.me.conradscott.blone.ast.attributeconstraint.SimpleAttributeConstraint;
import uk.me.conradscott.blone.ast.expression.Variable;

final class AttributeConstraintBuilder {
    private AttributeConstraintBuilder() {}

    static AttributeConstraintIfc build( final BLOneParser.AttributeConstraintContext ctx ) {
        return Visitor.s_instance.visit( ctx );
    }

    private static final class Visitor extends BLOneParserBaseVisitor< AttributeConstraintIfc > {
        private static final ParseTreeVisitor< AttributeConstraintIfc > s_instance = new Visitor();

        @Override
        public AttributeConstraintIfc visitCapturedAttributeConstraint( final BLOneParser.CapturedAttributeConstraintContext ctx ) {
            final Variable variable = VariableBuilder.build( ctx.Variable() );

            final SimpleAttributeConstraint simpleAttributeConstraint
                    = SimpleAttributeConstraintBuilder.build( ctx.simpleAttributeConstraint() );

            return new CapturedAttributeConstraint( LocationBuilder.build( ctx ), variable, simpleAttributeConstraint );
        }

        @Override
        public AttributeConstraintIfc visitSimpleAttributeConstraint( final BLOneParser.SimpleAttributeConstraintContext ctx ) {
            return SimpleAttributeConstraintBuilder.build( ctx );
        }
    }
}
