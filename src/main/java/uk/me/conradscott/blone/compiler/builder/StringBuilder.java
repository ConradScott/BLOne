package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

import java.util.Collection;
import java.util.stream.Collectors;

final class StringBuilder {
    private StringBuilder() {}

    // TODO: Remove quotes; replace string escapes.
    @NotNull private static StringLiteral build( @NotNull final LocationIfc location, @NotNull final String s ) {
        return new StringLiteral( location, s );
    }

    @NotNull static StringLiteral build( @NotNull final TerminalNode terminalNode ) {
        return build( LocationBuilder.build( terminalNode ), terminalNode.getText() );
    }

    @NotNull
    static StringLiteral build( @NotNull final LocationIfc location,
                                @NotNull final Collection< ? extends TerminalNode > terminalNodes )
    {
        return build( location, terminalNodes.stream().map( ParseTree::getText ).collect( Collectors.joining() ) );
    }
}
