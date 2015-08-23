package uk.me.conradscott.blone.compiler.printer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    static void print( @NotNull final PrintStream out,
                       @NotNull final PrimitiveLiteralIfc< ? > literal,
                       final int depth )
    {
        new Visitor( out ).visit( literal, depth );
    }

    private static final class Visitor implements LiteralVisitorIfc< Integer, Void > {
        @NotNull private final PrintStream m_out;

        private Visitor( @NotNull final PrintStream out ) {
            m_out = out;
        }

        @Nullable @Override public Void visit( @NotNull final BooleanLiteral literal, @NotNull final Integer depth ) {
            Formatter.begin( m_out, literal, depth );
            Formatter.format( m_out, "value", literal.getValue().toString(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override public Void visit( @NotNull final CharacterLiteral literal, @NotNull final Integer depth ) {
            Formatter.begin( m_out, literal, depth );
            Formatter.format( m_out, "value", literal.getValue().toString(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final DoubleFloatingPointLiteral literal, @NotNull final Integer depth ) {
            Formatter.begin( m_out, literal, depth );
            Formatter.format( m_out, "value", literal.getValue().toString(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final FloatingPointLiteral literal, @NotNull final Integer depth ) {
            Formatter.begin( m_out, literal, depth );
            Formatter.format( m_out, "value", literal.getValue().toString(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override public Void visit( @NotNull final IntegerLiteral literal, @NotNull final Integer depth ) {
            Formatter.begin( m_out, literal, depth );
            Formatter.format( m_out, "value", literal.getValue().toString(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override
        public Void visit( @NotNull final LongIntegerLiteral literal, @NotNull final Integer depth ) {
            Formatter.begin( m_out, literal, depth );
            Formatter.format( m_out, "value", literal.getValue().toString(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }

        @Nullable @Override public Void visit( @NotNull final StringLiteral literal, @NotNull final Integer depth ) {
            Formatter.begin( m_out, literal, depth );
            Formatter.format( m_out, "value", literal.getValue(), depth + 1 );
            Formatter.end( m_out );
            return null;
        }
    }
}
