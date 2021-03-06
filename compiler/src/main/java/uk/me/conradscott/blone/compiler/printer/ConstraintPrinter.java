package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.constraint.ConjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.constraint.ConstraintVisitorIfc;
import uk.me.conradscott.blone.ast.constraint.DisjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ExpressionConstraint;
import uk.me.conradscott.blone.ast.constraint.NegativeConstraint;

import java.io.PrintStream;

final class ConstraintPrinter {
    private ConstraintPrinter() {}

    static void print( final PrintStream out, final ConstraintIfc ce, final int depth ) {
        new Visitor( out ).visit( ce, depth );
    }

    private static final class Visitor implements ConstraintVisitorIfc< Integer, Visitor > {
        private final PrintStream m_out;

        private Visitor( final PrintStream out ) {
            m_out = out;
        }

        @Override public Visitor visit( final ExpressionConstraint constraint, final Integer depth ) {
            Formatter.begin( m_out, constraint, depth );
            ExpressionPrinter.print( m_out, constraint.getExpression(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final NegativeConstraint constraint, final Integer depth ) {
            Formatter.begin( m_out, constraint, depth );
            visit( constraint.getConstraint(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final ConjunctiveConstraint constraint, final Integer depth ) {
            Formatter.begin( m_out, constraint, depth );
            visit( constraint.getConjuncts(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final DisjunctiveConstraint constraint, final Integer depth ) {
            Formatter.begin( m_out, constraint, depth );
            visit( constraint.getDisjuncts(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }
    }
}
