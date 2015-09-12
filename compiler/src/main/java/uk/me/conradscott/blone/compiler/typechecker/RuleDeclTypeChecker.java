package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class RuleDeclTypeChecker {
    private RuleDeclTypeChecker() {}

    static void check( final ErrorCollectorIfc errorCollector,
                       final Iterable< RuleDecl > ruleDecls,
                       final ScopeIfc< RelationDecl > relationDecls )
    {
        ruleDecls.forEach( ruleDecl -> check( errorCollector, ruleDecl, relationDecls ) );
    }

    static void check( final ErrorCollectorIfc errorCollector,
                       final RuleDecl ruleDecl,
                       final ScopeIfc< RelationDecl > relationDecls )
    {
        ConditionElementTypeChecker.check( errorCollector,
                                           ruleDecl.getConditionElement(),
                                           new SymbolTable(),
                                           relationDecls );
    }
}
