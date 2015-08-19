package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.ast.BinOp;
import uk.me.conradscott.blone.ast.Location;
import uk.me.conradscott.blone.ast.LocationIfc;
import uk.me.conradscott.blone.ast.PrimitiveType;

import java.util.List;

final class Utils {
    private Utils() {}

    @NotNull static LocationIfc location(@NotNull final Token token) {
        return new Location(token.getTokenSource().getSourceName(), token.getLine(), token.getCharPositionInLine());
    }

    @NotNull static LocationIfc location(@NotNull final ParserRuleContext ctx) {
        return location(ctx.getStart());
    }

    @NotNull static PrimitiveType getPrimitiveType(@NotNull final ParseTree ctx) {
        return PrimitiveType.valueOf(BLOneLexer.VOCABULARY.getSymbolicName(ctx.accept(TerminalVisitor.INSTANCE)
                                                                              .getSymbol()
                                                                              .getType()));
    }

    @NotNull static BinOp getBinOp(@NotNull final ParseTree ctx) {
        return BinOp.valueOf(BLOneLexer.VOCABULARY.getSymbolicName(ctx.accept(TerminalVisitor.INSTANCE)
                                                                      .getSymbol()
                                                                      .getType()));
    }

    @NotNull static String concatenateStringLiterals(final List<TerminalNode> terminalNodes) {
        for (final TerminalNode node : terminalNodes) {
            System.out.println(node.getSymbol().getText());
        }

        return "";
    }
}
