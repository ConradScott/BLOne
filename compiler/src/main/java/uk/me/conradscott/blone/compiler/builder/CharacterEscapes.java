package uk.me.conradscott.blone.compiler.builder;

@SuppressWarnings( { "HardcodedLineSeparator", "HardcodedFileSeparator" } ) final class CharacterEscapes {
    @SuppressWarnings( "MagicNumber" ) private static final char[] s_literalEscapedCharValue = new char[ 256 ];

    static {
        s_literalEscapedCharValue[ 'n' ] = '\n';
        s_literalEscapedCharValue[ 'r' ] = '\r';
        s_literalEscapedCharValue[ 't' ] = '\t';
        s_literalEscapedCharValue[ 'b' ] = '\b';
        s_literalEscapedCharValue[ 'f' ] = '\f';
        s_literalEscapedCharValue[ '\\' ] = '\\';
        s_literalEscapedCharValue[ '\'' ] = '\'';
        s_literalEscapedCharValue[ '"' ] = '"';
    }

    private CharacterEscapes() {}

    static char getOneCharacter( final StringCursor cursor ) {
        final char ch = cursor.next();

        // Not an escape character
        if ( ch != '\\' ) {
            return ch;
        }

        final char next = cursor.next();

        // C-style escape character.
        if ( ( next < s_literalEscapedCharValue.length ) && ( s_literalEscapedCharValue[ next ] != 0 ) ) {
            return s_literalEscapedCharValue[ next ];
        }

        // Octal escape character.
        if ( ( next >= '0' ) && ( next <= '7' ) ) {
            return getOctalEscape( cursor, next );
        }

        // Unknown escape character.
        throw new IllegalArgumentException( "Unknown escape in string: \\" + next );
    }

    private static char getOctalEscape( final StringCursor cursor, char ch ) {
        final int end = Math.min( cursor.getIndex() + ( ( ch <= '3' ) ? 2 : 1 ), cursor.getEnd() );
        int codePoint = Character.digit( ch, 8 );

        while ( ( cursor.getIndex() != end ) && ( cursor.peek() >= '0' ) && ( cursor.peek() <= '7' ) ) {
            ch = cursor.next();

            codePoint *= 8;
            codePoint += Character.digit( ch, 8 );
        }

        return ( char ) codePoint;
    }
}
