package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.attributeconstraint.AttributeConstraintIfc;
import uk.me.conradscott.blone.ast.attributeconstraint.AttributeConstraintVisitorIfc;
import uk.me.conradscott.blone.ast.attributeconstraint.CapturedAttributeConstraint;
import uk.me.conradscott.blone.ast.attributeconstraint.SimpleAttributeConstraint;

import java.io.PrintStream;

final class AttributeConstraintPrinter {
    private AttributeConstraintPrinter() {}

    static void print( final PrintStream out,
                       final Iterable< AttributeConstraintIfc > attributeConstraints,
                       final int depth )
    {
        attributeConstraints.forEach( attributeConstraint -> print( out, attributeConstraint, depth ) );
    }

    static void print( final PrintStream out, final AttributeConstraintIfc attributeConstraint, final int depth ) {
        new Visitor( out ).visit( attributeConstraint, depth );
    }

    private static final class Visitor implements AttributeConstraintVisitorIfc< Integer, Visitor > {
        private final PrintStream m_out;

        private Visitor( final PrintStream out ) {
            m_out = out;
        }

        @Override public Visitor visit( final SimpleAttributeConstraint attributeConstraint, final Integer depth ) {
            Formatter.begin( m_out, attributeConstraint, depth );
            Formatter.format( m_out, "name", attributeConstraint.getName(), depth + 1 );
            ConstraintPrinter.print( m_out, attributeConstraint.getConstraint(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final CapturedAttributeConstraint attributeConstraint, final Integer depth )
        {
            Formatter.begin( m_out, attributeConstraint, depth );
            VariablePrinter.print( m_out, attributeConstraint.getCaptureVariable(), depth + 1 );
            visit( attributeConstraint.getAttributeConstraint(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }
    }
}
