package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public final class Lexer {
    private Lexer() {}

    public static Token getSingleTokenFromString( final String s ) {
        final BLOneLexer lexer = new BLOneLexer( new ANTLRInputStream( s ) );
        final BufferedTokenStream tokens = new BufferedTokenStream( lexer );

        final Token token = tokens.LT( 1 );

        assertNotNull( "The lexical analyser should return one token.", token );
        assertNotEquals( "The second token should not be an EOF token.", Token.EOF, token.getType() );

        final Token eof = tokens.LT( 2 );

        assertNotNull( "The lexical analyser should return a second token.", eof );
        assertEquals( "The second token should be an EOF token.", Token.EOF, eof.getType() );
        assertEquals( "The entire input should be consumed by the first token.",
                      s.length(),
                      eof.getCharPositionInLine() );

        return token;
    }

    private static final class Listener extends BaseErrorListener {
        private String m_msg = null;

        @Override
        public void syntaxError( final Recognizer< ?, ? > recognizer,
                                 final Object offendingSymbol,
                                 final int line,
                                 final int charPositionInLine,
                                 final String msg,
                                 final RecognitionException e )
        {
            m_msg = msg;
        }
    }

    static String getSingleInvalidTokenFromString( final String s ) {
        final BLOneLexer lexer = new BLOneLexer( new ANTLRInputStream( s ) );
        lexer.removeErrorListeners();

        final Listener listener = new Listener();
        lexer.addErrorListener( listener );

        final BufferedTokenStream tokens = new BufferedTokenStream( lexer );

        final Token eof = tokens.LT( 1 );

        assertNotNull( "The lexical analyser should return a token.", eof );
        assertEquals( "The second token should be an EOF token.", Token.EOF, eof.getType() );
        assertEquals( "The entire input should be consumed by the token.", s.length(), eof.getCharPositionInLine() );

        assertNotNull( "The lexer should have reported a syntax error.", listener.m_msg );

        return listener.m_msg;
    }
}
