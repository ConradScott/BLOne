package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

import javax.annotation.Nullable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Functions to help test the BLOne lexical analyser.
 */
public final class Lexer {
    private static final class Listener extends BaseErrorListener {
        @Nullable private String m_msg = null;

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

        @Nullable String getMsg() {
            return m_msg;
        }
    }

    private Lexer() {}

    /**
     * Tokenize an input string which is consumed by a single valid token.
     *
     * @param s A string to tokenize.
     * @return A token containing the entire input string.
     */
    public static Token getSingleTokenFromString( final String s ) {
        final BLOneLexer lexer = new BLOneLexer( new ANTLRInputStream( s ) );
        final TokenStream tokens = new BufferedTokenStream( lexer );

        final Token token = tokens.LT( 1 );

        assert token.getCharPositionInLine() == token.getStartIndex();

        assertNotNull( "The lexical analyser should return one token.", token );
        assertNotEquals( "The first token should not be an EOF token.", Token.EOF, token.getType() );
        assertEquals( "The first token should start at the beginning of the input string.", 0, token.getStartIndex() );
        assertEquals( "The entire input should be consumed by the first token.", s.length(), token.getStopIndex() + 1 );

        final Token eof = tokens.LT( 2 );

        assert eof.getCharPositionInLine() == eof.getStartIndex();

        assertNotNull( "The lexical analyser should return a second token.", eof );
        assertEquals( "The second token should be an EOF token.", Token.EOF, eof.getType() );
        assertEquals( "The second token should not contain any text.", s.length(), eof.getStartIndex() );
        assertEquals( "The second token should not contain any text.", s.length(), eof.getStopIndex() + 1 );

        return token;
    }

    /**
     * Tokenize an input string where the invalid token is the entire input string.
     *
     * @param s A string to tokenize.
     * @return A syntax error reported by the lexer.
     */
    public static String getSingleInvalidTokenFromString( final String s ) {
        final BLOneLexer lexer = new BLOneLexer( new ANTLRInputStream( s ) );
        lexer.removeErrorListeners();

        final Listener listener = new Listener();
        lexer.addErrorListener( listener );

        final TokenStream tokens = new BufferedTokenStream( lexer );

        final Token token = tokens.LT( 1 );

        assertNotNull( "The lexical analyser should return a token.", token );
        assertEquals( "The token should be an EOF token.", Token.EOF, token.getType() );
        assertEquals( "The entire input should be consumed by the token.", s.length(), token.getCharPositionInLine() );

        @Nullable final String msg = listener.getMsg();

        assertNotNull( "The lexer should have reported a syntax error.", msg );

        return msg;
    }
}
