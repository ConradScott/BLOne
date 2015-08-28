package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.constraint.AttributeConstraint;

import java.io.PrintStream;

final class AttributeConstraintPrinter {
    private AttributeConstraintPrinter() {}

    static void print( final PrintStream out,
                       final Iterable< AttributeConstraint > attributeConstraints,
                       final int depth )
    {
        attributeConstraints.forEach( attributeConstraint -> print( out, attributeConstraint, depth ) );
    }

    static void print( final PrintStream out, final AttributeConstraint attributeConstraint, final int depth ) {
        Formatter.begin( out, attributeConstraint, depth );

        Formatter.format( out, "name", attributeConstraint.getName(), depth + 1 );
        ConstraintPrinter.print( out, attributeConstraint.getConstraint(), depth + 1 );

        Formatter.end( out );
    }
}
