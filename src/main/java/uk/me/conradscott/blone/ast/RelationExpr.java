package uk.me.conradscott.blone.ast;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RelationExpr implements Iterable< AttributeExpr >, LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final Map< String, AttributeExpr > m_attributes = Maps.newLinkedHashMap();

    public RelationExpr( @NotNull final LocationIfc location, @NotNull final String name ) {
        m_location = location;
        m_name = name;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @Nullable public AttributeExpr put( @NotNull final AttributeExpr attributeExpr ) {
        final String name = attributeExpr.getName();

        final AttributeExpr previous = m_attributes.putIfAbsent( name, attributeExpr );

        if ( previous != null ) {
            assert previous.getName().equals( name );

            throw new ASTException( attributeExpr.getLocation()
                                    + ": an attribute with name '"
                                    + name
                                    + "' is already defined in relation '"
                                    + m_name
                                    + '\'' );
        }

        return attributeExpr;
    }

    @Nullable public AttributeExpr get( @NotNull final String name ) {
        final AttributeExpr attributeExpr = m_attributes.get( name );

        if ( attributeExpr == null ) {
            throw new ASTException( "No attribute with name '" + name + "' has been defined" );
        }

        assert attributeExpr.getName().equals( name );

        return attributeExpr;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
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
