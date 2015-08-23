package uk.me.conradscott.blone.ast.scope;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.type.RelationDecl;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RelationScope implements ScopeIfc< String, RelationDecl > {
    @NotNull private final Map< String, RelationDecl > m_relationDecls = Maps.newLinkedHashMap();

    @NotNull @Override public RelationDecl put( @NotNull final RelationDecl value ) {
        final String key = value.getName();

        final RelationDecl previous = m_relationDecls.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( value.getLocation()
                                    + ": a relation with name '"
                                    + key
                                    + "' is already defined at "
                                    + previous.getLocation() );
        }

        return value;
    }

    @NotNull @Override public RelationDecl get( @NotNull final String key ) {
        final RelationDecl value = m_relationDecls.get( key );

        if ( value == null ) {
            throw new ASTException( "No relation with name  '" + key + "' has been defined" );
        }

        assert value.getName().equals( key );

        return value;
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
