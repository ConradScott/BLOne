package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;

final class StringBuilder {
    private StringBuilder() {}

    @NotNull static String build(@NotNull final Collection<? extends ParseTree> terminalNodes) {
        return terminalNodes.stream().map(StringBuilder::build).collect(Collectors.joining());
    }

    // TODO: Remove quotes; replace string escapes.
    @NotNull static String build(final ParseTree parseTree) {
        return parseTree.getText();
    }
}
