package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import uk.me.conradscott.blone.ast.location.Location;
import uk.me.conradscott.blone.ast.location.LocationIfc;

final class LocationBuilder {
    private LocationBuilder() {}

    static LocationIfc build( final ParserRuleContext ctx ) {
        return build( ctx.getStart() );
    }

    static LocationIfc build( final TerminalNode terminalNode ) {
        return build( terminalNode.getSymbol() );
    }

    private static LocationIfc build( final Token token ) {
        return new Location( token.getTokenSource().getSourceName(), token.getLine(), token.getCharPositionInLine() );
    }
}
