package uk.me.conradscott.blone.antlr4;

import javax.annotation.Nullable;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat( token ).as( "The lexical analyser should return one token." ).isNotNull();
        assertThat( token.getType() ).as( "The first token should not be an EOF token." ).isNotEqualTo( Token.EOF );
        assertThat( token.getStartIndex() ).as( "The first token should start at the beginning of the input string." )
                                           .isEqualTo( 0 );
        assertThat( token.getStopIndex() + 1 ).as( "The entire input should be consumed by the first token." )
                                              .isEqualTo( s.length() );

        final Token eof = tokens.LT( 2 );

        assert eof.getCharPositionInLine() == eof.getStartIndex();

        assertThat( eof ).as( "The lexical analyser should return a second token." ).isNotNull();
        assertThat( eof.getType() ).as( "The second token should be an EOF token." ).isEqualTo( Token.EOF );
        assertThat( eof.getStartIndex() ).as( "The second token should start at the end of the input." )
                                         .isEqualTo( s.length() );
        assertThat( eof.getStopIndex() + 1 ).as( "The second token should not contain any text." )
                                            .isEqualTo( eof.getStartIndex() );

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

        assertThat( token ).as( "The lexical analyser should return a token." ).isNotNull();
        assertThat( token.getType() ).as( "The token should be an EOF token." ).isEqualTo( Token.EOF );
        assertThat( token.getStartIndex() ).as( "The token should start at the end of the input." )
                                           .isEqualTo( s.length() );
        assertThat( token.getStopIndex() + 1 ).as( "The token should not contain any text." )
                                              .isEqualTo( token.getStartIndex() );

        @Nullable final String msg = listener.getMsg();

        assertThat( msg ).as( "The lexer should have reported a syntax error." ).isNotNull();

        return msg;
    }
}
