package uk.me.conradscott.blone.compiler.builder;

import javax.annotation.Nullable;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

final class DocumentationStringBuilder {
    private DocumentationStringBuilder() {}

    @Nullable static StringLiteral build( @Nullable final BLOneParser.DocumentationStringContext ctx ) {
        return ( ctx == null ) ? null : StringBuilder.build( LocationBuilder.build( ctx ), ctx.StringLiteral() );
    }
}
