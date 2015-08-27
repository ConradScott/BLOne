package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.type.AttributeDecl;
import uk.me.conradscott.blone.ast.type.RelationDecl;

final class RelationDeclBuilder {
    private RelationDeclBuilder() {}

    static RelationDecl build( final BLOneParser.RelationDeclContext ctx ) {
        final Token name = ctx.Identifier().getSymbol();

        final RelationDecl relationDecl = new RelationDecl( LocationBuilder.build( ctx ),
                                                            name.getText(),
                                                            DocumentationStringBuilder.build( ctx.documentationString() ) );

        for ( final BLOneParser.AttributeDeclContext attributeDeclCtx : ctx.attributeDecl() ) {
            relationDecl.put( new AttributeDecl( LocationBuilder.build( attributeDeclCtx ),
                                                 attributeDeclCtx.Identifier().getText(),
                                                 PrimitiveTypeBuilder.build( attributeDeclCtx.type() ) ) );
        }

        return relationDecl;
    }
}
