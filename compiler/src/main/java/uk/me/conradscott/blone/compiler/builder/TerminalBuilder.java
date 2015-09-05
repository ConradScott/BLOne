package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import uk.me.conradscott.blone.antlr4.BLOneParserBaseVisitor;

import javax.annotation.Nullable;

final class TerminalBuilder {
    private TerminalBuilder() {}

    static TerminalNode build( final ParseTree ctx ) {
        return Visitor.s_instance.visit( ctx );
    }

    private static final class Visitor extends BLOneParserBaseVisitor< TerminalNode > {
        private static final ParseTreeVisitor< TerminalNode > s_instance = new Visitor();

        @Override public TerminalNode visitTerminal( final TerminalNode node ) {
            return node;
        }

        @Override
        protected TerminalNode aggregateResult( @Nullable final TerminalNode aggregate, final TerminalNode nextResult )
        {
            assert aggregate == null;
            return nextResult;
        }
    }
}
