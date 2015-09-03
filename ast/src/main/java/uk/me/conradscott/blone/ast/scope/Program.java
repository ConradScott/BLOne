package uk.me.conradscott.blone.ast.scope;

import com.google.common.collect.Lists;
import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.ast.type.RelationDecl;

import java.util.Collections;
import java.util.List;

public final class Program {
    private final List< ActionIfc > m_actions = Lists.newLinkedList();
    private final RelationScope m_relations = new RelationScope();
    private final RuleScope m_rules = new RuleScope();

    public List< ActionIfc > getActions() {
        return Collections.unmodifiableList( m_actions );
    }

    public void addAction( final ActionIfc action ) {
        m_actions.add( action );
    }

    public ScopeIfc< String, RelationDecl > getRelationDecls() {
        return UnmodifiableScope.instance( m_relations );
    }

    public void putRelationDecl( final RelationDecl decl ) {
        m_relations.put( decl );
    }

    public ScopeIfc< String, RuleDecl > getRuleDecls() {
        return UnmodifiableScope.instance( m_rules );
    }

    public void putRuleDecl( final RuleDecl decl ) {
        m_rules.put( decl );
    }
}
