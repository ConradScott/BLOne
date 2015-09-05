package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.literal.BooleanLiteral;
import uk.me.conradscott.blone.ast.literal.CharacterLiteral;
import uk.me.conradscott.blone.ast.literal.DoubleFloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.FloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.IntegerLiteral;
import uk.me.conradscott.blone.ast.literal.LongIntegerLiteral;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

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
            return new IntegerLiteral( LocationBuilder.build( ctx ),
                                       IntegerParser.parseIntLiteral( ctx.IntegerLiteral().getText() ) );
        }

        @Override public PrimitiveLiteralIfc< ? > visitLongInteger( final BLOneParser.LongIntegerContext ctx ) {
            return new LongIntegerLiteral( LocationBuilder.build( ctx ),
                                           IntegerParser.parseLongLiteral( ctx.LongIntegerLiteral().getText() ) );
        }

        @Override public PrimitiveLiteralIfc< ? > visitFloatingPoint( final BLOneParser.FloatingPointContext ctx ) {
            return new FloatingPointLiteral( LocationBuilder.build( ctx ),
                                             FloatingPointParser.parseFloatLiteral( ctx.FloatingPointLiteral()
                                                                                       .getText() ) );
        }

        @Override
        public PrimitiveLiteralIfc< ? > visitDoubleFloatingPoint( final BLOneParser.DoubleFloatingPointContext ctx ) {
            return new DoubleFloatingPointLiteral( LocationBuilder.build( ctx ),
                                                   FloatingPointParser.parseDoubleLiteral( ctx.DoubleFloatingPointLiteral()
                                                                                              .getText() ) );
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
