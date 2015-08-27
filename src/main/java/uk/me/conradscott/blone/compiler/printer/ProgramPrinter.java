package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.compiler.builder.ProgramBuilder;

import java.io.PrintStream;

public final class ProgramPrinter {
    private ProgramPrinter() {}

    public static void print( final PrintStream out, final ProgramBuilder program ) {
        RelationDeclPrinter.print( out, program.getRelationScope(), 0 );
        RuleDeclPrinter.print( out, program.getRuleScope(), 0 );
    }
}
