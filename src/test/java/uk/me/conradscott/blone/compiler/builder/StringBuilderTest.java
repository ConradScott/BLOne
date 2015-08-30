package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.junit.Test;
import uk.me.conradscott.blone.antlr4.Lexer;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

import static org.junit.Assert.assertEquals;

@SuppressWarnings( { "HardcodedFileSeparator", "HardcodedLineSeparator" } ) public final class StringBuilderTest {
    @Test public void testLiteralsWithoutEscapes() {
        final String expected = "abc";
        final String actual = getString( "\"abc\"" );
        assertEquals( "String literals with no escapes should be parsed without change.", expected, actual );
    }

    @Test public void testEscapes() {
        final String expected = "\b\t\n\f\r\"\'\\";
        final String actual = getString( "\"\\b\\t\\n\\f\\r\\\"\\'\\\\\"" );
        assertEquals( "Escapes should be replaced with the corresponding character.", expected, actual );
    }

    @Test public void testOctalEscapes() {
        @SuppressWarnings( "ConfusingOctalEscapeSequence" ) final String expected
                = "a\0b\1c\2d\3d\012e\12f\127f\1111\111";
        final String actual = getString( "\"a\\0b\\1c\\2d\\3d\\012e\\12f\\127f\\1111\\111\"" );
        assertEquals( "Octal escapes should be replaced with the corresponding ASCII character.", expected, actual );
    }

    private static String getString( final String s ) {
        final Token token = Lexer.getSingleTokenFromString( s );
        final StringLiteral literal = StringBuilder.build( new TerminalNodeImpl( token ) );

        return literal.getValue();
    }
}
