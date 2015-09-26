package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;

import java.io.PrintStream;

final class VariablePrinter {
    private VariablePrinter() {}

    static void print( final PrintStream out, final Iterable< IdentifierIfc > identifiers, final int depth ) {
        identifiers.forEach( relationExpr -> print( out, relationExpr, depth ) );
    }

    static void print( final PrintStream out, final IdentifierIfc identifier, final int depth ) {
        Formatter.format( out, identifier, identifier.getName(), depth );
    }
}
