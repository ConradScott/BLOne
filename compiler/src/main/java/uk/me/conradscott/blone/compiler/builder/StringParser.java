package uk.me.conradscott.blone.compiler.builder;

import java.util.List;

@SuppressWarnings( "HardcodedFileSeparator" ) final class StringParser {
    private StringParser() {}

    static String parseLiteral( final String literal ) {
        assert literal.length() >= 2 : "String literal is too short";

        final int end = literal.length() - 1;

        assert literal.charAt( 0 ) == '\"' : "String literal doesn't start with a double quote";
        assert literal.charAt( end ) == '\"' : "String literal doesn't end with a double quote";

        // Optimize conversion of strings with no escapes.
        return ( literal.indexOf( '\\' ) == -1 ) ? literal.substring( 1, end )
                                                 : parseLiteralInto( new StringBuilder( literal.length() - 2 ),
                                                                     literal ).toString();
    }

    static String parseLiterals( final List< String > literals ) {
        switch ( literals.size() ) {
        case 0:
            return "";

        case 1:
            return parseLiteral( literals.get( 0 ) );

        default:
            final int capacity = literals.stream().mapToInt( String::length ).sum();

            final StringBuilder builder = new StringBuilder( capacity );

            literals.forEach( s -> parseLiteralInto( builder, s ) );

            return builder.toString();
        }
    }

    private static CharSequence parseLiteralInto( final StringBuilder builder, final String s ) {
        assert s.length() >= 2 : "String literal is too short";

        final int end = s.length() - 1;

        assert s.charAt( 0 ) == '\"' : "String literal doesn't start with a double quote";
        assert s.charAt( end ) == '\"' : "String literal doesn't end with a double quote";

        final StringCursor cursor = new StringCursor( s, 1, end );

        while ( !cursor.atEnd() ) {
            builder.append( CharacterEscapes.getOneCharacter( cursor ) );
        }

        return builder;
    }
}
