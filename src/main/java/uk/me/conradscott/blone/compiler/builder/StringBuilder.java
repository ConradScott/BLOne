package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings( { "HardcodedLineSeparator", "HardcodedFileSeparator" } ) final class StringBuilder {
    @SuppressWarnings( "MagicNumber" ) @NotNull private static final char[] s_literalEscapedCharValue = new char[ 256 ];

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

    private StringBuilder() {}

    @NotNull static StringLiteral build( @NotNull final TerminalNode terminalNode ) {
        return new StringLiteral( LocationBuilder.build( terminalNode ), parseLiteral( terminalNode.getText() ) );
    }

    @NotNull
    static StringLiteral build( @NotNull final LocationIfc location,
                                @NotNull final Collection< ? extends TerminalNode > terminalNodes )
    {
        final List< String > strings = terminalNodes.stream().map( ParseTree::getText ).collect( Collectors.toList() );

        return new StringLiteral( location, parseLiteral( strings ) );
    }

    @NotNull private static String parseLiteral( @NotNull final String s ) {
        assert s.length() >= 2 : "String literal is too short";

        // Skip final double quote.
        final int end = s.length() - 1;

        assert s.charAt( 0 ) == '\"' : "String literal doesn't start with a double quote";
        assert s.charAt( end ) == '\"' : "String literal doesn't end with a double quote";

        return ( s.indexOf( '\\' ) == -1 ) ? s.substring( 1, end )
                                           : parseLiteralInto( new java.lang.StringBuilder( s.length() - 2 ),
                                                               s ).toString();
    }

    @NotNull private static String parseLiteral( @NotNull final List< String > strings ) {
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

    @NotNull
    private static CharSequence parseLiteralInto( @NotNull final java.lang.StringBuilder builder,
                                                  @NotNull final String s )
    {
        assert s.length() >= 2 : "String literal is too short";

        // Skip final double quote.
        final int end = s.length() - 1;

        assert s.charAt( 0 ) == '\"' : "String literal doesn't start with a double quote";
        assert s.charAt( end ) == '\"' : "String literal doesn't end with a double quote";

        // Skip initial double quote.
        int i = 1;
        char c = s.charAt( i );

        while ( i != end ) {
            if ( c == '\\' ) {
                i += 1;
                assert i != end : "Escape character as last character of string literal";
                c = s.charAt( i );

                if ( ( c < s_literalEscapedCharValue.length ) && ( s_literalEscapedCharValue[ c ] != 0 ) ) {
                    builder.append( s_literalEscapedCharValue[ c ] );

                    i += 1;
                    c = s.charAt( i );
                } else if ( ( c >= '0' ) && ( c <= '7' ) ) {
                    final int max = Math.min( i + ( ( c <= '3' ) ? 3 : 2 ), end );
                    int result = Character.digit( c, 8 );

                    i += 1;
                    c = s.charAt( i );

                    while ( ( i != max ) && ( c >= '0' ) && ( c <= '7' ) ) {
                        result *= 8;
                        result += Character.digit( c, 8 );

                        i += 1;
                        c = s.charAt( i );
                    }

                    builder.append( ( char ) result );
                } else {
                    assert false : "Unknown escape in string: \\" + c;
                }
            } else {
                builder.append( c );

                i += 1;
                c = s.charAt( i );
            }
        }

        return builder;
    }
}
