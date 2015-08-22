package uk.me.conradscott.blone.ast;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RuleScope implements ScopeIfc< String, RuleDecl > {
    private final Map< String, RuleDecl > m_ruleDecls = Maps.newLinkedHashMap();

    @Nullable @Override public RuleDecl put( @NotNull final RuleDecl value ) {
        final String name = value.getName();

        final RuleDecl previous = m_ruleDecls.putIfAbsent( name, value );

        if ( previous != null ) {
            assert previous.getName().equals( name );

            throw new ASTException( value.getLocation()
                                    + ": a rule with name '"
                                    + name
                                    + "' is already defined at "
                                    + previous.getLocation() );
        }

        return value;
    }

    @Nullable @Override public RuleDecl get( @NotNull final String key ) {
        final RuleDecl decl = m_ruleDecls.get( key );

        if ( decl == null ) {
            throw new ASTException( "No rule with name  '" + key + "' has been defined" );
        }

        assert decl.getName().equals( key );

        return decl;
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
