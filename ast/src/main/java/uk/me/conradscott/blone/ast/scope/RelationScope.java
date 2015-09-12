package uk.me.conradscott.blone.ast.scope;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.type.RelationDecl;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;

final class RelationScope implements ScopeIfc< RelationDecl > {
    private final Map< String, RelationDecl > m_relationDecls = Maps.newLinkedHashMap();

    @Override public RelationDecl get( final String key ) {
        @Nullable final RelationDecl value = m_relationDecls.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public RelationDecl put( final RelationDecl value ) {
        final String key = value.getName();

        @Nullable final RelationDecl previous = m_relationDecls.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( "A relation with name '"
                                    + key
                                    + "' is already defined at "
                                    + previous.getLocation() );
        }

        return value;
    }

    @Override public Stream< RelationDecl > stream() {
        return m_relationDecls.values().stream();
    }

    @Override public Iterator< RelationDecl > iterator() {
        return m_relationDecls.values().iterator();
    }

    @Override public void forEach( final Consumer< ? super RelationDecl > action ) {
        m_relationDecls.values().forEach( action );
    }

    @Override public Spliterator< RelationDecl > spliterator() {
        return m_relationDecls.values().spliterator();
    }
}
