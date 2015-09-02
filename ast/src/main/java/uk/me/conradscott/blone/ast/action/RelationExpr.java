package uk.me.conradscott.blone.ast.action;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public final class RelationExpr implements ScopeIfc< String, AttributeExpr >, LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final Map< String, AttributeExpr > m_attributes = Maps.newLinkedHashMap();

    public RelationExpr( final LocationIfc location, final String name ) {
        m_location = location;
        m_name = name;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    @Override public AttributeExpr put( final AttributeExpr value ) {
        final String key = value.getName();

        @Nullable final AttributeExpr previous = m_attributes.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( "A value for the attribute '"
                                    + key
                                    + "' has already been given in the expression for '"
                                    + m_name
                                    + '\'' );
        }

        return value;
    }

    @Override public AttributeExpr get( final String key ) {
        @Nullable final AttributeExpr value = m_attributes.get( key );

        if ( value == null ) {
            throw new ASTException( "No value for the attribute '" + key + "' has been given in the expression for '"
                                    + m_name + '\'' );
        }

        assert value.getName().equals( key );

        return value;
    }

    @Override public Iterator< AttributeExpr > iterator() {
        return m_attributes.values().iterator();
    }

    @Override public void forEach( final Consumer< ? super AttributeExpr > action ) {
        m_attributes.values().forEach( action );
    }

    @Override public Spliterator< AttributeExpr > spliterator() {
        return m_attributes.values().spliterator();
    }
}
