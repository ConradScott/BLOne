package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import uk.me.conradscott.blone.antlr4.Lexer;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings( { "HardcodedFileSeparator", "HardcodedLineSeparator" } ) public final class CharacterParserTest {
    @Test public void testLiteralsWithoutEscapes() {
        final Character expected = 'a';
        final Character actual = getCharacter( "\'a\'" );
        assertThat( actual ).as( "Character literals with no escapes should be parsed without change." )
                            .isEqualTo( expected );
    }

    @Test public void testEscapes() {
        final String[] literals = { "\'\\b\'",
                                    "\'\\t\'",
                                    "\'\\n\'",
                                    "\'\\f\'",
                                    "\'\\r\'",
                                    "\'\\\"\'",
                                    "\'\\'\'",
                                    "\'\\\\\'" };

        final char[] expected = { '\b', '\t', '\n', '\f', '\r', '\"', '\'', '\\' };

        assert expected.length == literals.length : "The two input arrays should be the same length.";

        final char[] actual = new char[ expected.length ];

        for ( int i = 0; i != expected.length; ++i ) {
            actual[ i ] = getCharacter( literals[ i ] );
        }

        assertThat( actual ).as( "Escapes should be replaced with the corresponding character." ).isEqualTo( expected );
    }

    @Test public void testOctalEscapes() {
        final String[] literals = { "\'\\0\'",
                                    "\'\\1\'",
                                    "\'\\3\'",
                                    "\'\\4\'",
                                    "\'\\7\'",
                                    "\'\\012\'",
                                    "\'\\12\'",
                                    "\'\\127\'",
                                    "\'\\111\'",
                                    "\'\\11\'" };

        final char[] expected = { '\0', '\1', '\3', '\4', '\7', '\012', '\12', '\127', '\111', '\11' };

        assert expected.length == literals.length : "The two input arrays should be the same length.";

        final char[] actual = new char[ expected.length ];

        for ( int i = 0; i != expected.length; ++i ) {
            actual[ i ] = getCharacter( literals[ i ] );
        }

        assertThat( actual ).as( "Escapes should be replaced with the corresponding character." ).isEqualTo( expected );
    }

    private static char getCharacter( final String s ) {
        final Token token = Lexer.getSingleTokenFromString( s );
        return CharacterParser.parseLiteral( token.getText() );
    }
}
