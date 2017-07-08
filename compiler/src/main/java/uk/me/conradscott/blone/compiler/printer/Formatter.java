package uk.me.conradscott.blone.compiler.printer;

import java.io.PrintStream;

import org.eclipse.collections.impl.utility.StringIterate;

import uk.me.conradscott.blone.ast.location.LocatedIfc;

final class Formatter {
    private static final String INDENT = "    ";

    private Formatter() {}

    static void begin( final PrintStream out, final LocatedIfc located, final int depth ) {
        out.format( "%n%s<%s: ", indent( depth ), located.getClass().getSimpleName() );
    }

    static void end( final PrintStream out ) {
        out.append( '>' );
    }

    static void format( final PrintStream out, final LocatedIfc located, final String value, final int depth )
    {
        out.format( "%n%s<%s: %s>", indent( depth ), located.getClass().getSimpleName(), value );
    }

    static void format( final PrintStream out, final String key, final String value, final int depth )
    {
        out.format( "%n%s%s: %s", indent( depth ), key, value );
    }

    private static String indent( final int depth ) {
        return StringIterate.repeat( INDENT, depth );
    }
}
