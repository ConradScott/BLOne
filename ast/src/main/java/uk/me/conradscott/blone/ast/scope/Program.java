package uk.me.conradscott.blone.ast.scope;

import com.gs.collections.api.RichIterable;
import com.gs.collections.api.list.ImmutableList;
import com.gs.collections.impl.factory.Lists;
import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.rule.RuleDecl;
import uk.me.conradscott.blone.ast.type.RelationDecl;

public final class Program {
    private static final Program EMPTY = new Program( Lists.immutable.empty(),
                                                      RelationScope.empty(),
                                                      RuleScope.empty() );

    private final ImmutableList< ActionIfc > m_actions;
    private final RelationScope m_relations;
    private final RuleScope m_rules;

    public static Program empty() {
        return EMPTY;
    }

    public Program( final RelationDecl decl ) {
        this( Lists.immutable.empty(), RelationScope.empty().put( decl ), RuleScope.empty() );
    }

    public Program( final RuleDecl decl ) {
        this( Lists.immutable.empty(), RelationScope.empty(), RuleScope.empty().put( decl ) );
    }

    public Program( final ActionIfc decl ) {
        this( Lists.immutable.of( decl ), RelationScope.empty(), RuleScope.empty() );
    }

    private Program( final ImmutableList< ActionIfc > actions,
                     final RelationScope relationScope,
                     final RuleScope ruleScope )
    {
        m_actions = actions;
        m_relations = relationScope;
        m_rules = ruleScope;
    }

    public Program put( final Program program ) {
        Program result = this;

        for ( final ActionIfc action : program.m_actions ) {
            result = result.addAction( action );
        }

        for ( final RelationDecl decl : program.m_relations ) {
            result = result.putRelationDecl( decl );
        }

        for ( final RuleDecl decl : program.m_rules ) {
            result = result.putRuleDecl( decl );
        }

        return result;
    }

    public RichIterable< ActionIfc > getActions() {
        return m_actions;
    }

    public Program addAction( final ActionIfc action ) {
        return new Program( m_actions.newWith( action ), m_relations, m_rules );
    }

    public ScopeIfc< RelationDecl > getRelationDecls() {
        return m_relations;
    }

    public Program putRelationDecl( final RelationDecl decl ) {
        return new Program( m_actions, m_relations.put( decl ), m_rules );
    }

    public ScopeIfc< RuleDecl > getRuleDecls() {
        return m_rules;
    }

    public Program putRuleDecl( final RuleDecl decl ) {
        return new Program( m_actions, m_relations, m_rules.put( decl ) );
    }
}
