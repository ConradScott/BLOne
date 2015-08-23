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
        ce.accept( new Visitor( out ), depth );
    }

    private static final class Visitor implements ConditionElementVisitorIfc< Integer, Void > {
        @NotNull private final PrintStream m_out;

        private Visitor( @NotNull final PrintStream out ) {
            m_out = out;
        }

        @Nullable @Override
        public Void visitCapturedCE( @NotNull final CapturedCE capturedCE, @NotNull final Integer depth ) {
            Formatter.begin( m_out, capturedCE, depth );
            Formatter.format( m_out, "variable", capturedCE.getVariable().getName(), depth + 1 );
            capturedCE.getConditionElement().accept( this, depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visitConjunctiveCE( @NotNull final ConjunctiveCE conjunctiveCE, @NotNull final Integer depth ) {
            Formatter.begin( m_out, conjunctiveCE, depth );
            visit( conjunctiveCE.getConjuncts(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visitDisjunctiveCE( @NotNull final DisjunctiveCE disjunctiveCE, @NotNull final Integer depth ) {
            Formatter.begin( m_out, disjunctiveCE, depth );
            visit( disjunctiveCE.getDisjuncts(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visitExistentialCE( @NotNull final ExistentialCE existentialCE, @NotNull final Integer depth ) {
            Formatter.begin( m_out, existentialCE, depth );
            visit( existentialCE.getPredicate(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visitNegativeCE( @NotNull final NegativeCE negativeCE, @NotNull final Integer depth ) {
            Formatter.begin( m_out, negativeCE, depth );
            visit( negativeCE.getConditionElement(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visitPatternCE( @NotNull final PatternCE patternCE, @NotNull final Integer depth ) {
            Formatter.begin( m_out, patternCE, depth );
            m_out.print( patternCE.getName() + "..." ); // TODO
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visitUniversalCE( @NotNull final UniversalCE universalCE, @NotNull final Integer depth ) {
            Formatter.begin( m_out, universalCE, depth );
            visit( universalCE.getRange(), depth + 1 );
            visit( universalCE.getPredicate(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }
    }
}
