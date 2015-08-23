package uk.me.conradscott.blone.compiler.printer;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocatedIfc;

import java.io.PrintStream;
import java.util.Map;

final class Formatter {
    @NotNull private static final String INDENT = "    ";

    @NotNull private static final Map< Integer, String > s_indentCache = Maps.newHashMap();

    private Formatter() {}

    static void begin( @NotNull final PrintStream out, @NotNull final LocatedIfc located, final int depth ) {
        out.format( "%n%s<%s: ", Formatter.indent( depth ), located.getClass().getSimpleName() );
    }

    static void end( @NotNull final PrintStream out ) {
        out.append( '>' );
    }

    static void format( @NotNull final PrintStream out,
                        @NotNull final String key,
                        @NotNull final String value,
                        final int depth )
    {
        out.format( "%n%s%s: %s", Formatter.indent( depth ), key, value );
    }

    @NotNull private static String indent( final int depth ) {
        return s_indentCache.computeIfAbsent( depth, k -> Strings.repeat( INDENT, k ) );
    }
}
