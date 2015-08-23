package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.type.PrimitiveType;

final class PrimitiveTypeBuilder {
    private PrimitiveTypeBuilder() {}

    @NotNull static PrimitiveType build( @NotNull final BLOneParser.TypeContext ctx ) {
        final Token type = TerminalBuilder.build( ctx.primitiveType() ).getSymbol();

        return PrimitiveType.valueOf( BLOneLexer.VOCABULARY.getSymbolicName( type.getType() ) );
    }
}
