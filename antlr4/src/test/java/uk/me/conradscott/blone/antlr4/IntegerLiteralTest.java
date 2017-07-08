package uk.me.conradscott.blone.antlr4;

import org.antlr.v4.runtime.Token;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class IntegerLiteralTest {
    @Test public void testValidIntegers() {
        final String[] literals = { "0", "0x0", "0X0", "00", "0b0", "0B0" };

        for ( final String expected : literals ) {
            final Token token = Lexer.getSingleTokenFromString( expected );
            final String actual = token.getText();

            assertThat( actual ).as( "Integer literal " +
                                     expected +
                                     " should be returned untouched by the lexical analyser" ).isEqualTo( expected );
        }
    }
}
