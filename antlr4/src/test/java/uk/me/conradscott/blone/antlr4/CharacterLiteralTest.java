package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings( { "HardcodedFileSeparator", "HardcodedLineSeparator" } ) public final class CharacterLiteralTest {
    private static final String[] VALID_CHARACTER_LITERALS = { "\'a\'", "\'\\\\\'", "\'\\\"\'", "\'\\\\\'", "\'\\t\'" };

    @Test public void testValidCharacters() {
        for ( final String expected : VALID_CHARACTER_LITERALS ) {
            final Token token = Lexer.getSingleTokenFromString( expected );
            final String actual = token.getText();

            assertEquals( "Character literal " + expected + " should be returned untouched by the lexical analyser",
                          expected,
                          actual );
        }
    }

    private static final String[][] INVALID_CHARACTER_LITERALS = {
        /* Empty character literal */ { "\'\'", "\'\'" },
        /* Character literal too long */ { "\'aa\'", "\'" },
        /* Unterminated escape */ { "\'\\\'", "\'\\\'" },
        /* Unknown escape string */ { "\'\\x\'", "\'" },
        /* Unterminated character literal */ { "\'", "\'" },
        /* Unterminated character literal */ { "\'\\t", "\'\\t" },
        /* Over-long octal escape */ { "\'\\423", "\'\\423" },
        /* Over-long octal escape */ { "\'\\0123", "\'\\0123" },
        /* Malformed octal escape */ { "\'\\8", "\'\\8" } };

    @Test public void testInvalidCharacters() {
        for ( final String[] entry : INVALID_CHARACTER_LITERALS ) {

            final String actual = Lexer.getSingleInvalidTokenFromString( entry[ 0 ] );
            final String expected = String.format( "token recognition error at: '%s'", entry[ 1 ] );

            assertEquals( "Character literal "
                          + entry[ 0 ]
                          + " should have raised the syntax error \'"
                          + expected
                          + "\'.", expected, actual );
        }
    }
}