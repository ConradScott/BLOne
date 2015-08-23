package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;
import uk.me.conradscott.blone.ast.literal.BooleanLiteral;
import uk.me.conradscott.blone.ast.literal.CharacterLiteral;
import uk.me.conradscott.blone.ast.literal.DoubleFloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.FloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.IntegerLiteral;
import uk.me.conradscott.blone.ast.literal.LongIntegerLiteral;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;

final class LiteralBuilder {
    private LiteralBuilder() {}

    @NotNull static PrimitiveLiteralIfc< ? > build( final BLOneParser.LiteralContext ctx ) {
        return LiteralVisitor.s_instance.visit( ctx );
    }

    private static final class LiteralVisitor extends BLOneParserBaseVisitor< PrimitiveLiteralIfc< ? > > {
        private static final ParseTreeVisitor< PrimitiveLiteralIfc< ? > > s_instance = new LiteralVisitor();

        @Override public PrimitiveLiteralIfc< ? > visitBoolean( final BLOneParser.BooleanContext ctx ) {
            return new BooleanLiteral( LocationBuilder.build( ctx ), ctx.booleanLiteral().value );
        }

        @Override public PrimitiveLiteralIfc< ? > visitInteger( final BLOneParser.IntegerContext ctx ) {
            return new IntegerLiteral( LocationBuilder.build( ctx ), 0 /* ctx.IntegerLiteral() */ );
        }

        @Override public PrimitiveLiteralIfc< ? > visitLongInteger( final BLOneParser.LongIntegerContext ctx ) {
            return new LongIntegerLiteral( LocationBuilder.build( ctx ), 0 /* ctx.IntegerLiteral() */ );
        }

        @Override public PrimitiveLiteralIfc< ? > visitFloatingPoint( final BLOneParser.FloatingPointContext ctx ) {
            return new FloatingPointLiteral( LocationBuilder.build( ctx ), 0 /* ctx.IntegerLiteral() */ );
        }

        @Override
        public PrimitiveLiteralIfc< ? > visitDoubleFloatingPoint( final BLOneParser.DoubleFloatingPointContext ctx ) {
            return new DoubleFloatingPointLiteral( LocationBuilder.build( ctx ), 0 /* ctx.IntegerLiteral() */ );
        }

        @Override public PrimitiveLiteralIfc< ? > visitCharacter( final BLOneParser.CharacterContext ctx ) {
            return new CharacterLiteral( LocationBuilder.build( ctx ), '0' /* ctx.IntegerLiteral() */ );
        }

        @Override public PrimitiveLiteralIfc< ? > visitString( final BLOneParser.StringContext ctx ) {
            return StringBuilder.build( ctx.StringLiteral() );
        }

        @Override
        protected PrimitiveLiteralIfc< ? > aggregateResult( final PrimitiveLiteralIfc< ? > aggregate,
                                                            final PrimitiveLiteralIfc< ? > nextResult )
        {
            assert aggregate == null;
            return nextResult;
        }
    }
}
