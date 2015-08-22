package uk.me.conradscott.blone.ast.builder.antlr4;

import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.antlr4.BLOneParser;

final class DocumentationStringBuilder {
    private DocumentationStringBuilder() {}

    @Nullable static String build(@Nullable final BLOneParser.DocumentationStringContext ctx) {
        return (ctx == null) ? null : StringBuilder.build(ctx.StringLiteral());
    }
}
