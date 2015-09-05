package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.scope.Program;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

public final class ProgramTypeChecker {
    private ProgramTypeChecker() {}

    public static void check( final ErrorCollectorIfc errorCollector, final Program program ) {
        RuleDeclTypeChecker.check( errorCollector, program.getRuleDecls(), program.getRelationDecls() );
    }
}
