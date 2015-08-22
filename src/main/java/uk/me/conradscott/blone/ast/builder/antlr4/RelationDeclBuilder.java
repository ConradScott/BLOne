package uk.me.conradscott.blone.ast.builder.antlr4;

import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.AttributeDecl;
import uk.me.conradscott.blone.ast.RelationDecl;

final class RelationDeclBuilder {
    private RelationDeclBuilder() {}

    @NotNull static RelationDecl build( @NotNull final BLOneParser.RelationDeclContext ctx ) {
        final Token name = ctx.Identifier().getSymbol();

        final RelationDecl relationDecl = new RelationDecl( Locations.build( name ),
                                                            name.getText(),
                                                            DocumentationStringBuilder.build( ctx.documentationString() ) );

        for ( final BLOneParser.AttributeDeclContext attributeDeclCtx : ctx.attributeDecl() ) {
            assert attributeDeclCtx != null : "null AttributeDeclContext in RelationDeclContext";

            final AttributeDecl attributeDecl = new AttributeDecl( Locations.build( attributeDeclCtx.start ),
                                                                   attributeDeclCtx.Identifier().getText(),
                                                                   PrimitiveTypeBuilder.build( attributeDeclCtx.type() ) );

            relationDecl.put( attributeDecl );
        }

        return relationDecl;
    }
}
