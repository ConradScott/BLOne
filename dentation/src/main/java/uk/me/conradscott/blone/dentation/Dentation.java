package uk.me.conradscott.blone.dentation;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.function.Supplier;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.WritableToken;
import org.antlr.v4.runtime.misc.Pair;
import org.eclipse.collections.api.list.primitive.BooleanList;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;

public class Dentation {
    private final TokenQueue       m_tokenQueue       = new TokenQueue();
    private final IndentationStack m_indentationStack = new IndentationStack();

    private final TokenStream m_tokenStream;

    private final int m_nlType;
    private final int m_indentType;
    private final int m_dedentType;

    private final BooleanList m_beginBlockTokenTypes;

    private final Pair< TokenSource, CharStream > m_tokenFactorySourcePair;

    private boolean m_beginBlock = false;

    public Dentation( final Supplier< Token > nextToken,
                      final int nlType,
                      final int indentType,
                      final int dedentType,
                      final int[] beginBlockTokenTypes,
                      final Pair< TokenSource, CharStream > tokenFactorySourcePair )
    {
        m_tokenStream = new TokenStream( nextToken );

        m_nlType = nlType;
        m_indentType = indentType;
        m_dedentType = dedentType;

        m_beginBlockTokenTypes = buildImmutableBooleanListFrom( beginBlockTokenTypes );

        assert !isBeginBlockTokenType( nlType );
        assert !isBeginBlockTokenType( indentType );
        assert !isBeginBlockTokenType( dedentType );

        m_tokenFactorySourcePair = tokenFactorySourcePair;
    }

    public Token nextToken() {
        @Nullable Token head;

        while ( ( head = m_tokenQueue.poll() ) == null ) {
            final Token token = m_tokenStream.poll();

            assert m_indentationStack.peek() <= token.getCharPositionInLine();
            assert token.getType() != m_indentType && token.getType() != m_dedentType;

            if ( token.getType() == m_nlType ) {
                queueTokensFromNewline( token );
            } else if ( token.getType() == Token.EOF ) {
                queueTokensFromEOF( token );
            } else {
                if ( m_beginBlock ) {
                    m_beginBlock = false;

                    assert token.getCharPositionInLine() > m_indentationStack.peek();

                    // Begin a non-empty block.
                    m_indentationStack.push( token.getCharPositionInLine() );
                    m_tokenQueue.add( indentTokenFrom( token ) );
                }

                m_tokenQueue.add( token );
            }
        }

        if ( isBeginBlockTokenType( head.getType() ) ) {
            m_beginBlock = true;
        }

        return head;
    }

    private boolean isBeginBlockTokenType( final int nlType ) {
        return nlType > 0 && nlType < m_beginBlockTokenTypes.size() && m_beginBlockTokenTypes.get( nlType );
    }

    private void queueTokensFromNewline( final Token newline ) {
        assert newline.getType() == m_nlType;

        Token lastNewlineToken = newline;
        Token nextNonNewlineToken;

        // Eat blank lines, leaving the next non-newline token at the head of the token stream.
        while ( ( nextNonNewlineToken = m_tokenStream.peek() ).getType() == m_nlType ) {
            lastNewlineToken = m_tokenStream.poll();
        }

        assert nextNonNewlineToken.getType() != m_nlType;

        // Defer to the main loop's handling of EOF tokens.
        if ( nextNonNewlineToken.getType() == Token.EOF ) {
            return;
        }

        final int indentation = getIndentation( lastNewlineToken );

        final int offside = m_indentationStack.peek();

        if ( m_beginBlock ) {
            m_beginBlock = false;

            if ( indentation > offside ) {
                // Begin a non-empty block.
                m_indentationStack.push( indentation );
                m_tokenQueue.add( indentTokenFrom( lastNewlineToken ) );
            } else {
                // An empty block after a begin block keyword.
                m_tokenQueue.add( indentTokenFrom( newline ) );
                m_tokenQueue.add( dedentTokenFrom( lastNewlineToken ) );
            }
        }

        // Pop any indents of the stack that are after the current indentation.
        queueDedentTokens( lastNewlineToken, indentation );

        // If the current indentation doesn't match an existing indentation, mark an error.
        if ( indentation != m_indentationStack.peek() ) {
            m_tokenQueue.add( tokenFrom( lastNewlineToken, Token.INVALID_TYPE, "illegal indentation" ) );
        }
    }

    /**
     * Generate the sequence DEDENT* EOF.
     */
    private void queueTokensFromEOF( final Token eof ) {
        assert eof.getType() == Token.EOF;

        queueDedentTokens( eof, 0 );
        m_tokenQueue.add( eof );
    }

    private void queueDedentTokens( final Token token, final int indentation ) {
        assert indentation >= 0;

        while ( indentation < m_indentationStack.peek() ) {
            final int offside = m_indentationStack.pop();
            assert offside > indentation;

            m_tokenQueue.add( dedentTokenFrom( token ) );
        }

        assert m_indentationStack.peek() <= indentation;
    }

    private Token indentTokenFrom( final Token token ) {
        return tokenFrom( token, m_indentType, "?{" );
    }

    private Token dedentTokenFrom( final Token token ) {
        return tokenFrom( token, m_dedentType, "?}" );
    }

    private Token tokenFrom( final Token prototype, final int type, final String text ) {
        final WritableToken result = new CommonToken( m_tokenFactorySourcePair,
                                                      type,
                                                      Lexer.DEFAULT_TOKEN_CHANNEL,
                                                      prototype.getStartIndex(),
                                                      prototype.getStopIndex() );

        result.setText( text );

        return result;
    }

    @SuppressWarnings( "HardcodedLineSeparator" )
    private int getIndentation( final Token token ) {
        assert token.getType() == m_nlType;

        @Nullable final CharSequence text = token.getText();

        assert text != null;

        int index = 0;

        // Skip any leading EOL characters.
        while ( index != text.length() ) {
            final char ch = text.charAt( index );

            if ( ch != '\r' && ch != '\n' && ch != '\f' ) {
                break;
            }

            index += 1;
        }

        return text.length() - index;
    }

    private static BooleanList buildImmutableBooleanListFrom( final int[] items ) {
        assert Arrays.stream( items ).allMatch( item -> item >= 0 );

        final int length = Arrays.stream( items ).max().orElse( 0 );

        final boolean[] booleans = new boolean[ length + 1 ];

        for ( final int type : items ) {
            assert !booleans[ type ];
            booleans[ type ] = true;
        }

        return BooleanLists.immutable.with( booleans );
    }
}
