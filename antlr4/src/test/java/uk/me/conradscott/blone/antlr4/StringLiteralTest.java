package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings( "HardcodedFileSeparator" ) public final class StringLiteralTest {
    private static final String[] VALID_STRING_LITERALS = { "\"\"", "\"abc\"", "\"\\\"\"", "\"\\\\\"", "\"\\t\"" };

    @Test public void testValidStrings() {
        for ( final String expected : VALID_STRING_LITERALS ) {
            final Token token = Lexer.getSingleTokenFromString( expected );
            final String actual = token.getText();

            assertThat( actual ).as( "String literal " +
                                     expected +
                                     " should be returned untouched by the lexical analyser" ).isEqualTo( expected );
        }
    }

    private static final String[] INVALID_STRING_LITERALS = {
        /* Unterminated string escape */ "\"\\\"",
        /* Unknown string escape string */ "\"\\x",
        /* Unterminated string */ "\"",
        /* Unterminated string */ "\"\\t" };

    @Test public void testInvalidStrings() {
        for ( final String literal : INVALID_STRING_LITERALS ) {

            final String actual = Lexer.getSingleInvalidTokenFromString( literal );
            final String expected = String.format( "token recognition error at: '%s'", literal );

            assertThat( actual ).as( "String literal " +
                                     literal +
                                     " should have raised the syntax error \'" +
                                     expected +
                                     "\'." ).isEqualTo( expected );
        }
    }
}
