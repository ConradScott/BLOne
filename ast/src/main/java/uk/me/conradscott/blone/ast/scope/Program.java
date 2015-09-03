package uk.me.conradscott.blone.ast.scope;

import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.ast.type.RelationDecl;

public final class Program {
    private final ActionTable m_actions = new ActionTable();
    private final ScopeIfc< String, RelationDecl > m_relations = new RelationScope();
    private final ScopeIfc< String, RuleDecl > m_rules = new RuleScope();

    public void add( final ActionIfc action ) {
        m_actions.add( action );
    }

    public void put( final RelationDecl decl ) {
        m_relations.put( decl );
    }

    public void put( final RuleDecl decl ) {
        m_rules.put( decl );
    }

    public Iterable< ActionIfc > getActions() {
        return m_actions;
    }

    public Iterable< RelationDecl > getRelations() {
        return m_relations;
    }

    public Iterable< RuleDecl > getRules() {
        return m_rules;
    }
}
