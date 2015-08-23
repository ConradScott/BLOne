package uk.me.conradscott.blone.ast.statement;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RelationExpr implements ScopeIfc< String, AttributeExpr >, LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @NotNull private final Map< String, AttributeExpr > m_attributes = Maps.newLinkedHashMap();

    public RelationExpr( @NotNull final LocationIfc location, @NotNull final String name ) {
        m_location = location;
        m_name = name;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull @Override public AttributeExpr put( @NotNull final AttributeExpr value ) {
        final String key = value.getName();

        final AttributeExpr previous = m_attributes.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( value.getLocation()
                                    + ": an attribute with name '"
                                    + key
                                    + "' is already defined in relation '"
                                    + m_name
                                    + '\'' );
        }

        return value;
    }

    @NotNull @Override public AttributeExpr get( @NotNull final String key ) {
        final AttributeExpr value = m_attributes.get( key );

        if ( value == null ) {
            throw new ASTException( "No attribute with name '" + key + "' has been defined" );
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
