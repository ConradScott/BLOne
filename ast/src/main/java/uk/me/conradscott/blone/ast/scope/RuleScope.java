package uk.me.conradscott.blone.ast.scope;

import javax.annotation.Nullable;
import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Maps;

import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.rule.RuleDecl;

final class RuleScope implements ScopeIfc< RuleDecl > {
    private static final RuleScope EMPTY = new RuleScope( Maps.immutable.empty() );

    private final ImmutableMap< String, RuleDecl > m_ruleDecls;

    static RuleScope empty() {
        return EMPTY;
    }

    private RuleScope( final ImmutableMap< String, RuleDecl > ruleDecls ) {
        m_ruleDecls = ruleDecls;
    }

    @Override public RuleDecl get( final String key ) {
        @Nullable final RuleDecl value = m_ruleDecls.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public RuleScope put( final RuleDecl value ) {
        final String key = value.getName();

        @Nullable final LocatedIfc previous = get( key );

        if ( previous != null ) {
            throw new ASTException( value.getLocation(), "A rule with name '" + key + "' is already defined at " +
                                                         previous.getLocation() );
        }

        return new RuleScope( m_ruleDecls.newWithKeyValue( key, value ) );
    }

    @Override public RichIterable< RuleDecl > values() {
        return m_ruleDecls.valuesView();
    }

    @Override public Iterator< RuleDecl > iterator() {
        return m_ruleDecls.iterator();
    }
}
