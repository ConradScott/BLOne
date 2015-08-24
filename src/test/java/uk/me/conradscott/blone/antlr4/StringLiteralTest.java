package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringLiteralTest {
    @Test public void testStringLiteralLexicalAnalysis() {
        final String expected = "\"abc\"";
        final Token token = Lexer.getSingleTokenFromString( expected );
        final String actual = token.getText();

        assertEquals( "String literals should be returned untouched by the lexical analyser", expected, actual );
    }
}
