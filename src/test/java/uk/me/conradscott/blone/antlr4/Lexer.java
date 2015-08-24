package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class Lexer {
    private Lexer() {}

    @NotNull public static Token getSingleTokenFromString( final String s ) {
        final BLOneLexer lexer = new BLOneLexer( new ANTLRInputStream( s ) );
        final BufferedTokenStream tokens = new BufferedTokenStream( lexer );

        final Token token = tokens.LT( 1 );

        assertNotNull( "The lexical analyser should return one token.", token );

        final Token eof = tokens.LT( 2 );

        assertNotNull( "The lexical analyser should return a second token.", eof );
        assertEquals( "The second token should be an EOF token.", Token.EOF, eof.getType() );
        assertEquals( "The entire input should be consumed by the first token.",
                      s.length(),
                      eof.getCharPositionInLine() );

        return token;
    }
}
