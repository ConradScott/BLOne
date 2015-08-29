package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

final class StringBuilder {

    private StringBuilder() {}

    static StringLiteral build( final TerminalNode terminalNode ) {
        return new StringLiteral( LocationBuilder.build( terminalNode ), parseLiteral( terminalNode.getText() ) );
    }

    static StringLiteral build( final LocationIfc location, final Collection< ? extends TerminalNode > terminalNodes ) {
        final List< String > strings = terminalNodes.stream().map( ParseTree::getText ).collect( Collectors.toList() );

        return new StringLiteral( location, parseLiteral( strings ) );
    }

    private static String parseLiteral( final String s ) {
        assert s.length() >= 2 : "String literal is too short";

        final int end = s.length() - 1;

        assert s.charAt( 0 ) == '\"' : "String literal doesn't start with a double quote";
        assert s.charAt( end ) == '\"' : "String literal doesn't end with a double quote";

        // Optimize conversion of strings with no escapes.
        return ( s.indexOf( '\\' ) == -1 ) ? s.substring( 1, end )
                                           : parseLiteralInto( new java.lang.StringBuilder( s.length() - 2 ),
                                                               s ).toString();
    }

    private static String parseLiteral( final List< String > strings ) {
        switch ( strings.size() ) {
        case 0:
            return "";

        case 1:
            return parseLiteral( strings.get( 0 ) );

        default:
            final int capacity = strings.stream().mapToInt( String::length ).sum();

            final java.lang.StringBuilder builder = new java.lang.StringBuilder( capacity );

            strings.forEach( s -> parseLiteralInto( builder, s ) );

            return builder.toString();
        }
    }

    private static CharSequence parseLiteralInto( final java.lang.StringBuilder builder, final String s ) {
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
