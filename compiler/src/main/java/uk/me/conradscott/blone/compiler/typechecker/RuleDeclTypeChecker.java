package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class RuleDeclTypeChecker {
    private RuleDeclTypeChecker() {}

    static void typecheck( final ErrorCollectorIfc errorCollector,
                           final Iterable< RuleDecl > ruleDecls,
                           final ScopeIfc< RelationDecl > relationDecls )
    {
        ruleDecls.forEach( ruleDecl -> typecheck( errorCollector, ruleDecl, relationDecls ) );
    }

    static void typecheck( final ErrorCollectorIfc errorCollector,
                           final RuleDecl ruleDecl,
                           final ScopeIfc< RelationDecl > relationDecls )
    {
        ConditionElementTypeChecker.typecheck( errorCollector,
                                               ruleDecl.getConditionElement(),
                                               new SymbolTable(),
                                               relationDecls );
    }
}
