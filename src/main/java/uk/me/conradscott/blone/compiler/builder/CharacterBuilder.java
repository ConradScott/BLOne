package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.TerminalNode;
import uk.me.conradscott.blone.ast.literal.CharacterLiteral;

final class CharacterBuilder {

    private CharacterBuilder() {}

    static CharacterLiteral build( final TerminalNode terminalNode ) {
        return new CharacterLiteral( LocationBuilder.build( terminalNode ), parseLiteral( terminalNode.getText() ) );
    }

    private static char parseLiteral( final String s ) {
        assert s.length() >= 2 : "Character literal is too short";

        final int end = s.length() - 1;

        assert s.charAt( 0 ) == '\'' : "Character literal doesn't start with a single quote";
        assert s.charAt( end ) == '\'' : "Character literal doesn't end with a single quote";

        final StringCursor cursor = new StringCursor( s, 1, end );

        final char c = CharacterEscapes.getOneCharacter( cursor );

        assert cursor.atEnd() : "Extra characters at end of character literal.";

        return c;
    }
}
