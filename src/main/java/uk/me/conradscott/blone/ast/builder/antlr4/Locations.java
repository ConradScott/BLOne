package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.Location;
import uk.me.conradscott.blone.ast.LocationIfc;

final class Locations {
    private Locations() {}

    @NotNull static LocationIfc build( @NotNull final Token token ) {
        return new Location(token.getTokenSource().getSourceName(), token.getLine(), token.getCharPositionInLine());
    }

    @NotNull static LocationIfc build( @NotNull final ParserRuleContext ctx ) {
        return build( ctx.getStart() );
    }
}
