package uk.me.conradscott.blone.compiler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import uk.me.conradscott.blone.ast.location.Location;

import java.util.BitSet;

final class SyntaxErrorCollector extends BaseErrorListener {
    private final ErrorCollectorIfc m_collector;

    SyntaxErrorCollector( final ErrorCollectorIfc collector ) {
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

    private static Location location( final Recognizer< ?, ? > recognizer,
                                      final int line,
                                      final int charPositionInLine )
    {
        return new Location( recognizer.getInputStream().getSourceName(), line, charPositionInLine );
    }
}
