package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.scope.Program;

import java.io.PrintStream;

public final class ProgramPrinter {
    private ProgramPrinter() {}

    public static void print( final PrintStream out, final Program program ) {
        RelationDeclPrinter.print( out, program.getRelationDecls(), 0 );
        RuleDeclPrinter.print( out, program.getRuleDecls(), 0 );
        ActionPrinter.print( out, program.getActions(), 0 );
    }
}
