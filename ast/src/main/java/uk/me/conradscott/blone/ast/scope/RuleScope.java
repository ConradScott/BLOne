package uk.me.conradscott.blone.ast.scope;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.rule.RuleDecl;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public final class RuleScope implements ScopeIfc< String, RuleDecl > {
    private final Map< String, RuleDecl > m_ruleDecls = Maps.newLinkedHashMap();

    @Override public RuleDecl put( final RuleDecl value ) {
        final String key = value.getName();

        @Nullable final RuleDecl previous = m_ruleDecls.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( "A rule with name '" + key + "' is already defined at " + previous.getLocation() );
        }

        return value;
    }

    @Override public RuleDecl get( final String key ) {
        @Nullable final RuleDecl value = m_ruleDecls.get( key );

        if ( value == null ) {
            throw new ASTException( "No rule with name  '" + key + "' has been defined" );
        }

        assert value.getName().equals( key );

        return value;
    }

    @Override public Iterator< RuleDecl > iterator() {
        return m_ruleDecls.values().iterator();
    }

    @Override public void forEach( final Consumer< ? super RuleDecl > action ) {
        m_ruleDecls.values().forEach( action );
    }

    @Override public Spliterator< RuleDecl > spliterator() {
        return m_ruleDecls.values().spliterator();
    }
}
