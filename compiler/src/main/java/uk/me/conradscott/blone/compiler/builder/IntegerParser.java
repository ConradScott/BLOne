package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.ast.InternalASTException;

import java.util.Objects;
import java.util.regex.Pattern;

final class IntegerParser {
    private static final int HEX_RADIX = 16;
    private static final int DECIMAL_RADIX = 10;
    private static final int OCTAL_RADIX = 8;
    private static final int BINARY_RADIX = 2;

    private static final Pattern REMOVE_UNDERSCORES = Pattern.compile( "_", Pattern.LITERAL );

    private IntegerParser() {}

    static int parseIntLiteral( final String literal ) {
        // noinspection NumericCastThatLosesPrecision
        return ( int ) parseLiteral( literal, Integer.class );
    }

    static long parseLongLiteral( final String literal ) {
        return parseLiteral( literal, Long.class );
    }

    private static long parseLiteral( final String literal, final Class< ? extends Number > javaClass ) {
        assert Objects.equals( javaClass, Integer.class ) || Objects.equals( javaClass, Long.class );

        assert !literal.isEmpty() : "Integer literal too short.";

        final char last = literal.charAt( literal.length() - 1 );

        final int end;

        if ( ( last == 'l' ) || ( last == 'L' ) ) {
            assert Objects.equals( javaClass, Long.class )
                    : "Literal has a long integer type suffix but is being converted as an int.";

            end = literal.length() - 1;
        } else {
            assert Objects.equals( javaClass, Integer.class )
                    : "Literal has no type suffix but is being converted as a long.";

            end = literal.length();
        }

        try {
            return parseSignedDigits( literal, end, javaClass );
        } catch ( final NumberFormatException e ) {
            throw new InternalASTException( "Invalid integer literal '" + literal + "': " + e.getMessage(), e );
        }
    }

    private static long parseSignedDigits( final String literal,
                                           final int end,
                                           final Class< ? extends Number > javaClass )
    {
        assert end >= 1 : "Integer literal too short.";

        final int sgn;
        final int begin;

        switch ( literal.charAt( 0 ) ) {
        case '-':
            sgn = -1;
            begin = 1;
            break;

        case '+':
            sgn = 1;
            begin = 1;
            break;

        default:
            sgn = 1;
            begin = 0;
            break;
        }

        return parseDigits( literal, begin, end, sgn, javaClass );
    }

    private static long parseDigits( final String literal,
                                     final int begin,
                                     final int end,
                                     final int sgn,
                                     final Class< ? extends Number > javaClass )
    {
        assert begin >= 0 : "The start position must be non-negative.";
        assert begin <= end : "The start position must be before the end position.";
        assert end <= literal.length() : "The end position must not be past the end of the string.";

        final char first = literal.charAt( begin );

        if ( first != '0' ) {
            assert ( first >= '1' ) && ( first <= '9' ) : "Invalid initial digit in fixed-point literal.";

            return parseDigits( literal.substring( begin, end ), sgn, DECIMAL_RADIX, javaClass );
        }

        // '0' or '0l' or '0L'
        if ( end == ( begin + 1 ) ) {
            return 0L;
        }

        final char second = literal.charAt( begin + 1 );

        if ( ( ( second >= '0' ) && ( second <= '7' ) ) || ( second == '_' ) ) {
            return parseDigits( literal.substring( begin, end ), sgn, OCTAL_RADIX, javaClass );
        }

        if ( ( second == 'x' ) || ( second == 'X' ) ) {
            return parseDigits( literal.substring( begin + 2, end ), sgn, HEX_RADIX, javaClass );
        }

        if ( ( second == 'b' ) || ( second == 'B' ) ) {
            return parseDigits( literal.substring( begin + 2, end ), sgn, BINARY_RADIX, javaClass );
        }

        throw new InternalASTException( "Invalid integer literal '" + literal + '\'' );
    }

    private static long parseDigits( final String literal,
                                     final int sgn,
                                     final int radix,
                                     final Class< ? extends Number > javaClass )
    {
        assert Objects.equals( javaClass, Integer.class ) || Objects.equals( javaClass, Long.class );

        final String digits = getParsableForm( literal, sgn );

        return Objects.equals( javaClass, Integer.class ) ? Integer.parseInt( digits, radix )
                                                          : Long.parseLong( digits, radix );
    }

    private static String getParsableForm( final String literal, final int sgn ) {
        final String digits = removeUnderscores( literal );

        return ( sgn == -1 ) ? ( '-' + digits ) : digits;
    }

    private static String removeUnderscores( final String literal ) {
        return ( literal.indexOf( '_' ) == -1 ) ? literal : REMOVE_UNDERSCORES.matcher( literal ).replaceAll( "" );
    }
}
