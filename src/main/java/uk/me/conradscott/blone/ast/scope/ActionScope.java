package uk.me.conradscott.blone.ast.scope;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.action.ActionIfc;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.Nullable;

// TODO: Sort out this nonsense: Actions have no names so shouldn't be hanging around in a scope.
public final class ActionScope implements ScopeIfc< ActionIfc, ActionIfc > {
    private final Map< ActionIfc, ActionIfc > m_ruleDecls = Maps.newLinkedHashMap();

    @Override public ActionIfc put( final ActionIfc value ) {
        @Nullable final ActionIfc previous = m_ruleDecls.putIfAbsent( value, value );

        if ( previous != null ) {
            assert previous.equals( value );

            throw new ASTException( "An action '"
                                    + value
                                    + "' is already defined at "
                                    + previous.getLocation() );
        }

        return value;
    }

    @Override public ActionIfc get( final ActionIfc key ) {
        @Nullable final ActionIfc value = m_ruleDecls.get( key );

        if ( value == null ) {
            throw new ASTException( "No action '" + key + "' has been defined" );
        }

        assert value.equals( key );

        return value;
    }

    @Override public Iterator< ActionIfc > iterator() {
        return m_ruleDecls.values().iterator();
    }

    @Override public void forEach( final Consumer< ? super ActionIfc > action ) {
        m_ruleDecls.values().forEach( action );
    }

    @Override public Spliterator< ActionIfc > spliterator() {
        return m_ruleDecls.values().spliterator();
    }
}
