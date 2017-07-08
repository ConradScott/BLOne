package uk.me.conradscott.blone.compiler.builder;

import javax.annotation.Nullable;

import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

final class DocumentationStringBuilder {
    private DocumentationStringBuilder() {}

    @Nullable static StringLiteral build( @Nullable final BLOneParser.DocumentationStringContext ctx ) {
        if ( ctx == null ) {
            return null;
        } else {
            final ImmutableList< String > literals = Lists.immutable.withAll( ctx.StringLiteral() )
                                                                    .collect( ParseTree::getText );

            return new StringLiteral( LocationBuilder.build( ctx ), StringParser.parseLiterals( literals ) );
        }
    }
}
