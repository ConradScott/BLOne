package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.action.RelationExpr;

import java.io.PrintStream;

final class RelationExprPrinter {
    private RelationExprPrinter() {}

    static void print( final PrintStream out, final Iterable< RelationExpr > relationExprs, final int depth ) {
        relationExprs.forEach( relationExpr -> print( out, relationExpr, depth ) );
    }

    static void print( final PrintStream out, final RelationExpr relationExpr, final int depth ) {
        Formatter.begin( out, relationExpr, depth );

        Formatter.format( out, "name", relationExpr.getName(), depth + 1 );
        AttributeExprPrinter.print( out, relationExpr, depth + 1 );

        Formatter.end( out );
    }
}
