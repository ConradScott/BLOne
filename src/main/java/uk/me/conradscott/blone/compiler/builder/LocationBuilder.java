package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.Location;
import uk.me.conradscott.blone.ast.location.LocationIfc;

final class LocationBuilder {
    private LocationBuilder() {}

    @NotNull static LocationIfc build( @NotNull final ParserRuleContext ctx ) {
        return build( ctx.getStart() );
    }

    @NotNull static LocationIfc build( @NotNull final TerminalNode terminalNode ) {
        return build( terminalNode.getSymbol() );
    }

    @NotNull private static LocationIfc build( @NotNull final Token token ) {
        return new Location( token.getTokenSource().getSourceName(), token.getLine(), token.getCharPositionInLine() );
    }
}
