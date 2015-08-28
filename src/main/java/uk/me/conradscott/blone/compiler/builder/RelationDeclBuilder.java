package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.Token;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.type.AttributeDecl;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class RelationDeclBuilder {
    private RelationDeclBuilder() {}

    static RelationDecl build( final BLOneParser.RelationDeclContext ctx, final ErrorCollectorIfc errorCollector ) {
        final Token name = ctx.Identifier().getSymbol();

        final RelationDecl relationDecl = new RelationDecl( LocationBuilder.build( ctx ),
                                                            name.getText(),
                                                            DocumentationStringBuilder.build( ctx.documentationString() ) );

        for ( final BLOneParser.AttributeDeclContext context : ctx.attributeDecl() ) {
            final AttributeDecl attributeDecl = new AttributeDecl( LocationBuilder.build( context ),
                                                                   context.Identifier().getText(),
                                                                   PrimitiveTypeBuilder.build( context.type() ) );

            try {
                relationDecl.put( attributeDecl );
            } catch ( final ASTException e ) {
                errorCollector.error( attributeDecl.getLocation(), e.getMessage() );
            }
        }

        return relationDecl;
    }
}
