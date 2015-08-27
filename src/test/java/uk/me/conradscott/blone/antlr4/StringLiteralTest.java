package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings( "HardcodedFileSeparator" ) public class StringLiteralTest {
    private static final String[] VALID_STRINGS = { "\"\"", "\"abc\"", "\"\\\\\"", "\"\\\"\"", "\"\\\\\"", "\"\\t\"" };

    @Test public void testValidStrings() {
        for ( final String expected : VALID_STRINGS ) {
            final Token token = Lexer.getSingleTokenFromString( expected );
            final String actual = token.getText();

            assertEquals( "String literal " + expected + " should be returned untouched by the lexical analyser",
                          expected,
                          actual );
        }
    }

    private static final String[] INVALID_STRINGS = { /* Unterminated string escape */
                                                      "\"\\\"",
                                                                 /* Unknown string escape string */
                                                      "\"\\x",
                                                                 /* Unterminated string */
                                                      "\"",
                                                                 /* Unterminated string */
                                                      "\"\\t" };

    @Test public void testInvalidStrings() {
        for ( final String s : INVALID_STRINGS ) {

            final String actual = Lexer.getSingleInvalidTokenFromString( s );
            final String expected = String.format( "token recognition error at: '%s'", s );

            assertEquals( "String literal " + s + " should have raised the syntax error \'" + s + "\'.",
                          expected,
                          actual );
        }
    }
}
