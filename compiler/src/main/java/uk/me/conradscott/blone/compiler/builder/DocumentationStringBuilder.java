package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.ParseTree;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

final class DocumentationStringBuilder {
    private DocumentationStringBuilder() {}

    @Nullable static StringLiteral build( @Nullable final BLOneParser.DocumentationStringContext ctx ) {
        if ( ctx == null ) {
            return null;
        } else {
            final List< String > literals = ctx.StringLiteral()
                                               .stream()
                                               .map( ParseTree::getText )
                                               .collect( Collectors.toList() );

            return new StringLiteral( LocationBuilder.build( ctx ), StringParser.parseLiterals( literals ) );
        }
    }
}
