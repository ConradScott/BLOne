package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import uk.me.conradscott.blone.antlr4.Lexer;

import static org.assertj.core.api.Assertions.assertThat;

public final class IntegerParserTest {
    private static final String[] literals = { "0",
                                               "+0",
                                               "-0",
                                               "00",
                                               "0_0",
                                               "0__0",
                                               "0_00",
                                               "00_0",
                                               "0x0",
                                               "0X0",
                                               "00",
                                               "0b0",
                                               "0B0" };

    @Test public void testLiteralZeros() {
        final int[] expected = new int[ literals.length ];
        final int[] actual = new int[ literals.length ];

        for ( int i = 0; i != expected.length; ++i ) {
            expected[ i ] = 0;
            actual[ i ] = getInt( literals[ i ] );
        }

        assertThat( actual ).as( "Zero literals should equal zero." ).isEqualTo( expected );
    }

    @Test public void testLiteralZeroLongs() {
        final long[] expected = new long[ literals.length ];
        final long[] actual = new long[ literals.length ];

        for ( int i = 0; i != expected.length; ++i ) {
            expected[ i ] = 0L;
            actual[ i ] = getLong( literals[ i ] + 'L' );
        }

        assertThat( actual ).as( "Zero literals should equal zero." ).isEqualTo( expected );
    }

    private static int getInt( final String s ) {
        final Token token = Lexer.getSingleTokenFromString( s );
        return IntegerParser.parseIntLiteral( token.getText() );
    }

    private static long getLong( final String s ) {
        final Token token = Lexer.getSingleTokenFromString( s );
        return IntegerParser.parseLongLiteral( token.getText() );
    }
}
