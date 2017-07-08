package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import uk.me.conradscott.blone.antlr4.Lexer;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings( { "HardcodedFileSeparator", "HardcodedLineSeparator" } ) public final class StringParserTest {
    @Test public void testLiteralsWithoutEscapes() {
        final String expected = "abc";
        final String actual = getString( "\"abc\"" );
        assertThat( actual ).as( "String literals with no escapes should be parsed without change." )
                            .isEqualTo( expected );
    }

    @Test public void testEscapes() {
        final String expected = "\b\t\n\f\r\"\'\\";
        final String actual = getString( "\"\\b\\t\\n\\f\\r\\\"\\'\\\\\"" );
        assertThat( actual ).as( "Escapes should be replaced with the corresponding character." )
                            .isEqualTo( expected );
    }

    @Test public void testOctalEscapes() {
        @SuppressWarnings( "ConfusingOctalEscapeSequence" ) final String expected
                = "a\0b\1c\2d\3d\012e\12f\127f\1111\111";
        final String actual = getString( "\"a\\0b\\1c\\2d\\3d\\012e\\12f\\127f\\1111\\111\"" );
        assertThat( actual ).as( "Octal escapes should be replaced with the corresponding ASCII character." )
                            .isEqualTo( expected );
    }

    private static String getString( final String s ) {
        final Token token = Lexer.getSingleTokenFromString( s );
        return StringParser.parseLiteral( token.getText() );
    }
}
