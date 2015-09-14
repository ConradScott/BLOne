package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.expression.ExpressionIfc;
import uk.me.conradscott.blone.ast.expression.ExpressionVisitorIfc;
import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;

import java.io.PrintStream;

final class ExpressionPrinter {
    private ExpressionPrinter() {}

    static void print( final PrintStream out, final ExpressionIfc ce, final int depth ) {
        new Visitor( out ).visit( ce, depth );
    }

    private static final class Visitor implements ExpressionVisitorIfc< Integer, Visitor > {
        private final PrintStream m_out;

        private Visitor( final PrintStream out ) {
            m_out = out;
        }

        @Override public Visitor visit( final PrimitiveLiteralIfc< ? > literal, final Integer depth ) {
            LiteralPrinter.print( m_out, literal, depth );
            return this;
        }

        @Override public Visitor visit( final Variable variable, final Integer depth ) {
            Formatter.begin( m_out, variable, depth );
            VariablePrinter.print( m_out, variable, depth + 1 );
            Formatter.end( m_out );
            return this;
        }
    }
}
