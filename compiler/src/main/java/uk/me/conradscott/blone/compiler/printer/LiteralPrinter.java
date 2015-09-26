package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.literal.BooleanLiteral;
import uk.me.conradscott.blone.ast.literal.CharacterLiteral;
import uk.me.conradscott.blone.ast.literal.DoubleFloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.FloatingPointLiteral;
import uk.me.conradscott.blone.ast.literal.IntegerLiteral;
import uk.me.conradscott.blone.ast.literal.LiteralVisitorIfc;
import uk.me.conradscott.blone.ast.literal.LongIntegerLiteral;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

import java.io.PrintStream;

final class LiteralPrinter {
    private LiteralPrinter() {}

    static void print( final PrintStream out, final PrimitiveLiteralIfc< ? > literal, final int depth ) {
        new Visitor( out ).visit( literal, depth );
    }

    private static final class Visitor implements LiteralVisitorIfc< Integer, Visitor > {
        private final PrintStream m_out;

        private Visitor( final PrintStream out ) {
            m_out = out;
        }

        @Override public Visitor visit( final BooleanLiteral literal, final Integer depth ) {
            Formatter.format( m_out, literal, literal.getValue().toString(), depth );
            return this;
        }

        @Override public Visitor visit( final CharacterLiteral literal, final Integer depth ) {
            Formatter.format( m_out, literal, literal.getValue().toString(), depth );
            return this;
        }

        @Override public Visitor visit( final DoubleFloatingPointLiteral literal, final Integer depth ) {
            Formatter.format( m_out, literal, literal.getValue().toString(), depth );
            return this;
        }

        @Override public Visitor visit( final FloatingPointLiteral literal, final Integer depth ) {
            Formatter.format( m_out, literal, literal.getValue().toString(), depth );
            return this;
        }

        @Override public Visitor visit( final IntegerLiteral literal, final Integer depth ) {
            Formatter.format( m_out, literal, literal.getValue().toString(), depth );
            return this;
        }

        @Override public Visitor visit( final LongIntegerLiteral literal, final Integer depth ) {
            Formatter.format( m_out, literal, literal.getValue().toString(), depth );
            return this;
        }

        @Override public Visitor visit( final StringLiteral literal, final Integer depth ) {
            Formatter.format( m_out, literal, literal.getValue(), depth );
            return this;
        }
    }
}
