package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.ast.expression.BinOp;

final class BinOpBuilder {
    private BinOpBuilder() {}

    static BinOp build( final ParseTree ctx ) {
        final Token token = TerminalBuilder.build( ctx ).getSymbol();

        return BinOp.valueOf( BLOneLexer.VOCABULARY.getSymbolicName( token.getType() ) );
    }
}
