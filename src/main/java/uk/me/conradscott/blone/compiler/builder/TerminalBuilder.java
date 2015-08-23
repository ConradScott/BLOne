package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;

final class TerminalBuilder {
    private TerminalBuilder() {}

    @NotNull static TerminalNode build( @NotNull final ParseTree ctx ) {
        return ctx.accept( TerminalVisitor.INSTANCE );
    }

    private static final class TerminalVisitor extends BLOneParserBaseVisitor< TerminalNode > {
        static final TerminalVisitor INSTANCE = new TerminalVisitor();

        @Override public TerminalNode visitTerminal( final TerminalNode node ) {
            return node;
        }

        @Override protected TerminalNode aggregateResult( final TerminalNode aggregate, final TerminalNode nextResult ) {
            assert aggregate == null;
            return nextResult;
        }
    }
}
