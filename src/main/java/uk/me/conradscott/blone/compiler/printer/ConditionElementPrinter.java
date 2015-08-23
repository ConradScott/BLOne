package uk.me.conradscott.blone.compiler.printer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.conditionelement.CapturedCE;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementVisitorIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.DisjunctiveCE;
import uk.me.conradscott.blone.ast.conditionelement.ExistentialCE;
import uk.me.conradscott.blone.ast.conditionelement.NegativeCE;
import uk.me.conradscott.blone.ast.conditionelement.PatternCE;
import uk.me.conradscott.blone.ast.conditionelement.UniversalCE;

import java.io.PrintStream;

final class ConditionElementPrinter {
    private ConditionElementPrinter() {}

    static void print( @NotNull final PrintStream out, @NotNull final ConditionElementIfc ce, final int depth ) {
        new Visitor( out ).visit( ce, depth );
    }

    private static final class Visitor implements ConditionElementVisitorIfc< Integer, Void > {
        @NotNull private final PrintStream m_out;

        private Visitor( @NotNull final PrintStream out ) {
            m_out = out;
        }

        @Nullable @Override
        public Void visit( @NotNull final CapturedCE conditionElement, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            Formatter.format( m_out, "variable", conditionElement.getVariable().getName(), depth + 1 );
            visit( conditionElement.getConditionElement(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final ConjunctiveCE conditionElement, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getConjuncts(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final DisjunctiveCE conditionElement, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getDisjuncts(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final ExistentialCE conditionElement, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getPredicate(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final NegativeCE conditionElement, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getConditionElement(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final PatternCE conditionElement, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            Formatter.format( m_out, "name", conditionElement.getName(), depth + 1 );
            AttributeConstraintPrinter.print( m_out, conditionElement, depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final UniversalCE conditionElement, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getRange(), depth + 1 );
            visit( conditionElement.getPredicate(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }
    }
}
