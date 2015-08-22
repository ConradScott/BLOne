package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.BinOp;

import java.util.Collection;
import java.util.stream.Collectors;

final class BinOpBuilder {
    private BinOpBuilder() {}

    @NotNull static BinOp build(@NotNull final ParseTree ctx) {
        return BinOp.valueOf(BLOneLexer.VOCABULARY.getSymbolicName(ctx.accept(TerminalVisitor.INSTANCE)
                                                                      .getSymbol()
                                                                      .getType()));
    }
}
