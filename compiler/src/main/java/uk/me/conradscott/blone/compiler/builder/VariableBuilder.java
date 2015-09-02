package uk.me.conradscott.blone.compiler.builder;

import org.antlr.v4.runtime.tree.TerminalNode;
import uk.me.conradscott.blone.ast.expression.Variable;

final class VariableBuilder {
    private VariableBuilder() {}

    static Variable build( final TerminalNode node ) {
        return new Variable( LocationBuilder.build( node ), node.getText() );
    }
}
