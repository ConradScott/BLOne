package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.ast.BinOp;

final class BinOpBuilder {
    private BinOpBuilder() {}

    @NotNull static BinOp build( @NotNull final ParseTree ctx ) {
        return BinOp.valueOf( BLOneLexer.VOCABULARY.getSymbolicName( ctx.accept( TerminalVisitor.INSTANCE )
                                                                        .getSymbol()
                                                                        .getType() ) );
    }
}
