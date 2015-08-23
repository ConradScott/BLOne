package uk.me.conradscott.blone.compiler.printer;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.type.AttributeDecl;

import java.io.PrintStream;

final class AttributeDeclPrinter {
    private AttributeDeclPrinter() {}

    static void print( @NotNull final PrintStream out,
                       @NotNull final Iterable< AttributeDecl > attributeDecls,
                       final int depth )
    {
        attributeDecls.forEach( attributeDecl -> print( out, attributeDecl, depth ) );
    }

    static void print( @NotNull final PrintStream out, @NotNull final AttributeDecl attributeDecl, final int depth ) {
        Formatter.begin( out, attributeDecl, depth );

        Formatter.format( out, "name", attributeDecl.getName(), depth + 1 );
        Formatter.format( out, "type", attributeDecl.getType().getName(), depth + 1 );

        Formatter.end( out );
    }
}
