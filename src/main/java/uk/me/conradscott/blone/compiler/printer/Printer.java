package uk.me.conradscott.blone.compiler.printer;

import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.compiler.builder.ProgramBuilder;
import uk.me.conradscott.blone.ast.statement.RelationDecl;
import uk.me.conradscott.blone.ast.statement.RuleDecl;

import java.io.PrintStream;

public final class Printer {
    private Printer() {}

    public static void print( @NotNull final PrintStream out, final ProgramBuilder visitor ) {
        printRelationScope( out, visitor );

        printRuleScope( out, visitor );
    }

    private static void printRelationScope( @NotNull final PrintStream out, final ProgramBuilder visitor ) {
        for ( final RelationDecl relationDecl : visitor.getRelationScope() ) {
            printRelationDecl( out, relationDecl );
        }
    }

    private static void printRelationDecl( @NotNull final PrintStream out, final RelationDecl relationDecl ) {
        Formatter.begin( out, relationDecl, 0 );
        Formatter.format( out, "name", relationDecl.getName(), 1 );
        relationDecl.getDocumentationString()
                    .ifPresent( s -> Formatter.format( out, "documentationString", s.getValue(), 1 ) );

        //        for ( final AttributeDecl attributeDecl : relationDecl ) {
        //            BLOne.LOGGER.info( "\t(" + attributeDecl.getName() + ' ' + attributeDecl.getType().getName() + ')' );
        //        }

        Formatter.end( out );
    }

    private static void printRuleScope( @NotNull final PrintStream out, final ProgramBuilder visitor ) {
        for ( final RuleDecl ruleDecl : visitor.getRuleScope() ) {
            printRuleDecl( out, ruleDecl );
        }
    }

    private static void printRuleDecl( @NotNull final PrintStream out, @NotNull final RuleDecl ruleDecl ) {
        Formatter.begin( out, ruleDecl, 0 );
        Formatter.format( out, "name", ruleDecl.getName(), 1 );
        ruleDecl.getDocumentationString()
                .ifPresent( s -> Formatter.format( out, "documentationString", s.getValue(), 1 ) );
        ConditionElementPrinter.print( out, ruleDecl.getConditionElement(), 1 );
        Formatter.end( out );
    }
}
