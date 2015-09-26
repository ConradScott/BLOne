package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.action.TupleExpr;

import java.io.PrintStream;

final class TupleExprPrinter {
    private TupleExprPrinter() {}

    static void print( final PrintStream out, final Iterable< TupleExpr > tupleExprs, final int depth ) {
        tupleExprs.forEach( tupleExpr -> print( out, tupleExpr, depth ) );
    }

    static void print( final PrintStream out, final TupleExpr tupleExpr, final int depth ) {
        Formatter.begin( out, tupleExpr, depth );

        Formatter.format( out, "name", tupleExpr.getName(), depth + 1 );
        AttributeExprPrinter.print( out, tupleExpr, depth + 1 );

        Formatter.end( out );
    }
}
