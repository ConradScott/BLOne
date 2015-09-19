package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.action.ActionVisitorIfc;
import uk.me.conradscott.blone.ast.action.Assertion;
import uk.me.conradscott.blone.ast.action.Retraction;

import java.io.PrintStream;

final class ActionPrinter {
    private ActionPrinter() {}

    static void print( final PrintStream out, final Iterable< ActionIfc > actions, final int depth ) {
        actions.forEach( action -> print( out, action, depth ) );
    }

    static void print( final PrintStream out, final ActionIfc action, final int depth ) {
        new Visitor( out ).visit( action, depth );
    }

    private static final class Visitor implements ActionVisitorIfc< Integer, Visitor > {
        private final PrintStream m_out;

        private Visitor( final PrintStream out ) {
            m_out = out;
        }

        @Override public Visitor visit( final Assertion assertion, final Integer depth ) {
            Formatter.begin( m_out, assertion, depth );
            TupleExprPrinter.print( m_out, assertion.getTupleExpr(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }

        @Override public Visitor visit( final Retraction retraction, final Integer depth ) {
            Formatter.begin( m_out, retraction, depth );
            VariablePrinter.print( m_out, retraction.getVariable(), depth + 1 );
            Formatter.end( m_out );
            return this;
        }
    }
}
