package uk.me.conradscott.blone.compiler.printer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.constraint.CapturedConstraint;
import uk.me.conradscott.blone.ast.constraint.ConjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.constraint.ConstraintVisitorIfc;
import uk.me.conradscott.blone.ast.constraint.DisjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.LiteralConstraint;
import uk.me.conradscott.blone.ast.constraint.NegativeConstraint;
import uk.me.conradscott.blone.ast.constraint.VariableConstraint;

import java.io.PrintStream;

final class ConstraintPrinter {
    private ConstraintPrinter() {}

    static void print( @NotNull final PrintStream out, @NotNull final ConstraintIfc ce, final int depth ) {
        new Visitor( out ).visit( ce, depth );
    }

    private static final class Visitor implements ConstraintVisitorIfc< Integer, Void > {
        @NotNull private final PrintStream m_out;

        private Visitor( @NotNull final PrintStream out ) {
            m_out = out;
        }

        @Nullable @Override
        public Void visit( @NotNull final CapturedConstraint constraint, @NotNull final Integer depth )
        {
            Formatter.begin( m_out, constraint, depth );

            Formatter.format( m_out, "variable", constraint.getVariable().getName(), depth + 1 );
            visit( constraint.getConstraint(), depth + 1 );

            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final ConjunctiveConstraint constraint, @NotNull final Integer depth )
        {
            Formatter.begin( m_out, constraint, depth );
            visit( constraint.getConjuncts(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final DisjunctiveConstraint constraint, @NotNull final Integer depth )
        {
            Formatter.begin( m_out, constraint, depth );
            visit( constraint.getDisjuncts(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final LiteralConstraint constraint, @NotNull final Integer depth )
        {
            Formatter.begin( m_out, constraint, depth );
            LiteralPrinter.print( m_out, constraint.getLiteral(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final NegativeConstraint constraint, @NotNull final Integer depth )
        {
            Formatter.begin( m_out, constraint, depth );
            visit( constraint.getConstraint(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final VariableConstraint constraint, @NotNull final Integer depth )
        {
            Formatter.begin( m_out, constraint, depth );
            Formatter.format( m_out, "variable", constraint.getVariable().getName(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }
    }
}
