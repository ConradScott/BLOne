package uk.me.conradscott.blone.compiler.builder;

import sun.misc.FloatingDecimal;
import uk.me.conradscott.blone.ast.InternalASTException;

final class FloatingPointParser {
    private FloatingPointParser() {}

    static float parseFloatLiteral( final String literal ) {
        try {
            return FloatingDecimal.parseFloat( literal );
        } catch ( final NumberFormatException e ) {
            throw new InternalASTException( "Invalid floating point literal '" + literal + "': " + e.getMessage(), e );
        }
    }

    static double parseDoubleLiteral( final String literal ) {
        try {
            return FloatingDecimal.parseDouble( literal );
        } catch ( final NumberFormatException e ) {
            throw new InternalASTException( "Invalid floating point literal '" + literal + "': " + e.getMessage(), e );
        }
    }
}
