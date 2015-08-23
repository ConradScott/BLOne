package uk.me.conradscott.blone.ast.scope;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.statement.RuleDecl;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RuleScope implements ScopeIfc< String, RuleDecl > {
    @NotNull private final Map< String, RuleDecl > m_ruleDecls = Maps.newLinkedHashMap();

    @NotNull @Override public RuleDecl put( @NotNull final RuleDecl value ) {
        final String key = value.getName();

        final RuleDecl previous = m_ruleDecls.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( value.getLocation()
                                    + ": a rule with name '"
                                    + key
                                    + "' is already defined at "
                                    + previous.getLocation() );
        }

        return value;
    }

    @NotNull @Override public RuleDecl get( @NotNull final String key ) {
        final RuleDecl value = m_ruleDecls.get( key );

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
