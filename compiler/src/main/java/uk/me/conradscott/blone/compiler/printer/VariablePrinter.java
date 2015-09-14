package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;

import java.io.PrintStream;

final class VariablePrinter {
    private VariablePrinter() {}

    static void print( final PrintStream out, final Iterable< IdentifierIfc > idenfitiers, final int depth ) {
        idenfitiers.forEach( relationExpr -> print( out, relationExpr, depth ) );
    }

    static void print( final PrintStream out, final IdentifierIfc identifier, final int depth ) {
        Formatter.begin( out, identifier, depth );
        Formatter.format( out, "variable", identifier.getName(), depth + 1 );
        Formatter.end( out );
    }
}
