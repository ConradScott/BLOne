package uk.me.conradscott.blone.ast.scope;

import com.google.common.collect.Lists;
import uk.me.conradscott.blone.ast.action.ActionIfc;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class ActionTable implements Iterable< ActionIfc > {
    private final List< ActionIfc > m_actions = Lists.newLinkedList();

    public void add( final ActionIfc value ) {
        m_actions.add( value );
    }

    @Override public Iterator< ActionIfc > iterator() {
        return m_actions.iterator();
    }

    @Override public void forEach( final Consumer< ? super ActionIfc > action ) {
        m_actions.forEach( action );
    }

    @Override public Spliterator< ActionIfc > spliterator() {
        return m_actions.spliterator();
    }
}
