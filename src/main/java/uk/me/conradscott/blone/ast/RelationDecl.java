package uk.me.conradscott.blone.ast;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RelationDecl implements ScopeIfc< String, AttributeDecl >, LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @Nullable private final String m_documentationString;
    @NotNull private final Map< String, AttributeDecl > m_attributes = Maps.newLinkedHashMap();

    public RelationDecl( @NotNull final LocationIfc location,
                         @NotNull final String name,
                         @Nullable final String documentationString )
    {
        m_location = location;
        m_name = name;
        m_documentationString = documentationString;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @Nullable public String getDocumentationString() {
        return m_documentationString;
    }

    @Nullable @Override public AttributeDecl put( @NotNull final AttributeDecl value ) {
        final String name = value.getName();

        final AttributeDecl previous = m_attributes.putIfAbsent( name, value );

        if ( previous != null ) {
            assert previous.getName().equals( name );

            throw new ASTException( value.getLocation()
                                    + ": an attribute with name '"
                                    + name
                                    + "' is already defined in relation '"
                                    + m_name
                                    + '\'' );
        }

        return value;
    }

    @Nullable @Override public AttributeDecl get( @NotNull final String key ) {
        final AttributeDecl attributeDecl = m_attributes.get( key );

        if ( attributeDecl == null ) {
            throw new ASTException( "No attribute with name '" + key + "' has been defined" );
        }

        assert attributeDecl.getName().equals( key );

        return attributeDecl;
    }

    @Override public Iterator< AttributeDecl > iterator() {
        return m_attributes.values().iterator();
    }

    @Override public void forEach( final Consumer< ? super AttributeDecl > action ) {
        m_attributes.values().forEach( action );
    }

    @Override public Spliterator< AttributeDecl > spliterator() {
        return m_attributes.values().spliterator();
    }
}
