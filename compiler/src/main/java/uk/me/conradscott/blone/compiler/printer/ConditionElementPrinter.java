package uk.me.conradscott.blone.compiler.printer;

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

    static void print( final PrintStream out, final ConditionElementIfc ce, final int depth ) {
        new Visitor( out ).visit( ce, depth );
    }

    private static final class Visitor implements ConditionElementVisitorIfc< Integer, Visitor > {
        private final PrintStream m_out;

        private Visitor( final PrintStream out ) {
            m_out = out;
        }

        @Override public Visitor visit( final CapturedCE conditionElement, final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            Formatter.format( m_out, "variable", conditionElement.getCaptureVariable().getName(), depth + 1 );
            visit( conditionElement.getPatternCE(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final ConjunctiveCE conditionElement, final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getConjuncts(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final DisjunctiveCE conditionElement, final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getDisjuncts(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final ExistentialCE conditionElement, final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getPredicate(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final NegativeCE conditionElement, final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getConditionElement(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final PatternCE conditionElement, final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            Formatter.format( m_out, "name", conditionElement.getName(), depth + 1 );
            AttributeConstraintPrinter.print( m_out, conditionElement, depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final UniversalCE conditionElement, final Integer depth ) {
            Formatter.begin( m_out, conditionElement, depth );
            visit( conditionElement.getRange(), depth + 1 );
            visit( conditionElement.getPredicate(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }
    }
}
