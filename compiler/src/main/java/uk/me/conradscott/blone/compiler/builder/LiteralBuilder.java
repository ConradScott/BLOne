package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.InternalASTException;
import uk.me.conradscott.blone.ast.literal.BooleanLiteral;
import uk.me.conradscott.blone.ast.literal.CharacterLiteral;
import uk.me.conradscott.blone.ast.literal.DoubleFloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.FloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.IntegerLiteral;
import uk.me.conradscott.blone.ast.literal.LongIntegerLiteral;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import javax.annotation.Nullable;

final class LiteralBuilder {
    private LiteralBuilder() {}

    static PrimitiveLiteralIfc< ? > build( final BLOneParser.LiteralContext ctx ) {
        return Visitor.s_instance.visit( ctx );
    }

    private static final class Visitor extends BLOneParserBaseVisitor< PrimitiveLiteralIfc< ? > > {
        private static final ParseTreeVisitor< PrimitiveLiteralIfc< ? > > s_instance = new Visitor();

        @Override public PrimitiveLiteralIfc< ? > visitBoolean( final BLOneParser.BooleanContext ctx ) {
            return new BooleanLiteral( LocationBuilder.build( ctx ), ctx.booleanLiteral().value );
        }

        @Override public PrimitiveLiteralIfc< ? > visitInteger( final BLOneParser.IntegerContext ctx ) {
            final LocationIfc location = LocationBuilder.build( ctx );
            final String text = ctx.IntegerLiteral().getText();

            final int value;

            try {
                value = IntegerParser.parseIntLiteral( text );
            } catch ( final InternalASTException e ) {
                throw new ASTException( location, e );
            }

            return new IntegerLiteral( location, value );
        }

        @Override public PrimitiveLiteralIfc< ? > visitLongInteger( final BLOneParser.LongIntegerContext ctx ) {
            final LocationIfc location = LocationBuilder.build( ctx );
            final String text = ctx.LongIntegerLiteral().getText();

            final long value;

            try {
                value = IntegerParser.parseLongLiteral( text );
            } catch ( final InternalASTException e ) {
                throw new ASTException( location, e );
            }

            return new LongIntegerLiteral( location, value );
        }

        @Override public PrimitiveLiteralIfc< ? > visitFloatingPoint( final BLOneParser.FloatingPointContext ctx ) {
            final LocationIfc location = LocationBuilder.build( ctx );
            final String text = ctx.FloatingPointLiteral().getText();

            final float value;

            try {
                value = FloatingPointParser.parseFloatLiteral( text );
            } catch ( final InternalASTException e ) {
                throw new ASTException( location, e );
            }

            return new FloatingPointLiteral( location, value );
        }

        @Override
        public PrimitiveLiteralIfc< ? > visitDoubleFloatingPoint( final BLOneParser.DoubleFloatingPointContext ctx ) {
            final LocationIfc location = LocationBuilder.build( ctx );
            final String text = ctx.DoubleFloatingPointLiteral().getText();

            final double value;

            try {
                value = FloatingPointParser.parseDoubleLiteral( text );
            } catch ( final InternalASTException e ) {
                throw new ASTException( location, e );
            }

            return new DoubleFloatingPointLiteral( location, value );
        }

        @Override public PrimitiveLiteralIfc< ? > visitCharacter( final BLOneParser.CharacterContext ctx ) {
            return new CharacterLiteral( LocationBuilder.build( ctx ),
                                         CharacterParser.parseLiteral( ctx.CharacterLiteral().getText() ) );
        }

        @Override public PrimitiveLiteralIfc< ? > visitString( final BLOneParser.StringContext ctx ) {
            return new StringLiteral( LocationBuilder.build( ctx ),
                                      StringParser.parseLiteral( ctx.StringLiteral().getText() ) );
        }

        @Override
        protected PrimitiveLiteralIfc< ? > aggregateResult( @Nullable final PrimitiveLiteralIfc< ? > aggregate,
                                                            final PrimitiveLiteralIfc< ? > nextResult )
        {
            assert aggregate == null;
            return nextResult;
        }
    }
}
