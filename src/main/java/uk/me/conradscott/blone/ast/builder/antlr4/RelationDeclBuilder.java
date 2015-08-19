package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.AttributeDecl;
import uk.me.conradscott.blone.ast.RelationDecl;

final class RelationDeclBuilder {
    private RelationDeclBuilder() {}

    @NotNull static RelationDecl build(@NotNull final BLOneParser.RelationDeclContext ctx) {
        final Token name = ctx.Identifier().getSymbol();

        final RelationDecl relationDecl = new RelationDecl(Utils.location(name),
                                                           name.getText(),
                                                           getDocumentationString(ctx));

        for (final BLOneParser.AttributeDeclContext attributeDeclCtx : ctx.attributeDecl()) {
            assert attributeDeclCtx != null : "null AttributeDeclContext in RelationDeclContext";

            final AttributeDecl attributeDecl = new AttributeDecl(Utils.location(attributeDeclCtx.start),
                                                                  attributeDeclCtx.Identifier().getText(),
                                                                  Utils.getPrimitiveType(attributeDeclCtx.type()));

            relationDecl.put(attributeDecl);
        }

        return relationDecl;
    }

    @Nullable private static String getDocumentationString(@NotNull final BLOneParser.RelationDeclContext ctx) {
        final BLOneParser.DocumentationStringContext documentationStringContext = ctx.documentationString();

        return (documentationStringContext == null) ? null
                                                    : Utils.concatenateStringLiterals(documentationStringContext.StringLiteral());
    }
}
