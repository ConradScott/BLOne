package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.action.AttributeExpr;

import java.io.PrintStream;

final class AttributeExprPrinter {
    private AttributeExprPrinter() {}

    static void print( final PrintStream out, final Iterable< AttributeExpr > attributeExprs, final int depth ) {
        attributeExprs.forEach( attributeExpr -> print( out, attributeExpr, depth ) );
    }

    static void print( final PrintStream out, final AttributeExpr attributeExpr, final int depth ) {
        Formatter.begin( out, attributeExpr, depth );

        Formatter.format( out, "name", attributeExpr.getName(), depth + 1 );
        ExpressionPrinter.print( out, attributeExpr.getExpression(), depth + 1 );

        Formatter.end( out );
    }
}
