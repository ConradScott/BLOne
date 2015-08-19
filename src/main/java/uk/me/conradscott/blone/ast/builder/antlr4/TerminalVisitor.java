package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.tree.TerminalNode;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;

final class TerminalVisitor extends BLOneParserBaseVisitor<TerminalNode> {
    static final TerminalVisitor INSTANCE = new TerminalVisitor();

    @Override public TerminalNode visitTerminal(final TerminalNode node) {
        return node;
    }

    @Override protected TerminalNode aggregateResult(final TerminalNode aggregate, final TerminalNode nextResult) {
        assert aggregate == null;
        return nextResult;
    }
}
