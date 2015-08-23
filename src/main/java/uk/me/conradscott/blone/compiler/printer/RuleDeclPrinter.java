package uk.me.conradscott.blone.compiler.printer;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.statement.RuleDecl;

import java.io.PrintStream;

final class RuleDeclPrinter {
    private RuleDeclPrinter() {}

    static void print( @NotNull final PrintStream out, @NotNull final Iterable< RuleDecl > ruleDecls, final int depth ) {
        ruleDecls.forEach( ruleDecl -> print( out, ruleDecl, depth ) );
    }

    static void print( @NotNull final PrintStream out, @NotNull final RuleDecl ruleDecl, final int depth ) {
        Formatter.begin( out, ruleDecl, depth );

        Formatter.format( out, "name", ruleDecl.getName(), depth + 1 );
        ruleDecl.getDocumentationString()
                .ifPresent( s -> Formatter.format( out, "documentationString", s.getValue(), depth + 1 ) );
        ConditionElementPrinter.print( out, ruleDecl.getConditionElement(), depth + 1 );

        Formatter.end( out );
    }
}
