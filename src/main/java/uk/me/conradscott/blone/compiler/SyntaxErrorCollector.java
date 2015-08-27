package uk.me.conradscott.blone.compiler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.Location;

import java.util.BitSet;

final class SyntaxErrorCollector extends BaseErrorListener {
    @NotNull private final ErrorCollectorIfc m_collector;

    SyntaxErrorCollector( @NotNull final ErrorCollectorIfc collector ) {
        m_collector = collector;
    }

    @Override
    public void syntaxError( final Recognizer< ?, ? > recognizer,
                             final Object offendingSymbol,
                             final int line,
                             final int charPositionInLine,
                             final String msg,
                             final RecognitionException e )
    {
        final Location location = location( recognizer, line, charPositionInLine );
        m_collector.error( location, msg );
    }

    @Override
    public void reportAmbiguity( final Parser recognizer,
                                 final DFA dfa,
                                 final int startIndex,
                                 final int stopIndex,
                                 final boolean exact,
                                 final BitSet ambigAlts,
                                 final ATNConfigSet configs )
    {
        assert false;
    }

    @Override
    public void reportAttemptingFullContext( final Parser recognizer,
                                             final DFA dfa,
                                             final int startIndex,
                                             final int stopIndex,
                                             final BitSet conflictingAlts,
                                             final ATNConfigSet configs )
    {
        assert false;
    }

    @Override
    public void reportContextSensitivity( final Parser recognizer,
                                          final DFA dfa,
                                          final int startIndex,
                                          final int stopIndex,
                                          final int prediction,
                                          final ATNConfigSet configs )
    {
        assert false;
    }

    @NotNull
    private static Location location( final Recognizer< ?, ? > recognizer,
                                      final int line,
                                      final int charPositionInLine )
    {
        return new Location( recognizer.getInputStream().getSourceName(), line, charPositionInLine );
    }
}
