package uk.me.conradscott.blone.compiler.builder;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.declaration.AttributeDecl;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class RelationDeclBuilder {
    private RelationDeclBuilder() {}

    static RelationDecl build( final BLOneParser.RelationDeclContext ctx, final ErrorCollectorIfc errorCollector ) {
        final RelationDecl relationDecl = new RelationDecl( LocationBuilder.build( ctx ),
                                                            VariableBuilder.build( ctx.Identifier() ),
                                                            DocumentationStringBuilder.build( ctx.documentationString() ) );

        for ( final BLOneParser.AttributeDeclContext context : ctx.attributeDecl() ) {
            final AttributeDecl attributeDecl = new AttributeDecl( LocationBuilder.build( context ),
                                                                   VariableBuilder.build( context.Identifier() ),
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
