package uk.me.conradscott.blone.ast.type;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public final class RelationDecl implements ScopeIfc< AttributeDecl >, LocatedIfc, TypeIfc {
    private final LocationIfc m_location;
    private final String m_name;
    @Nullable private final StringLiteral m_documentationString;
    private final Map< String, AttributeDecl > m_attributes = Maps.newLinkedHashMap();

    public RelationDecl( final LocationIfc location,
                         final String name,
                         @Nullable final StringLiteral documentationString )
    {
        m_location = location;
        m_name = name;
        m_documentationString = documentationString;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public String getName() {
        return m_name;
    }

    public Optional< StringLiteral > getDocumentationString() {
        return Optional.ofNullable( m_documentationString );
    }

    @Nullable @Override public AttributeDecl get( final String key ) {
        @Nullable final AttributeDecl value = m_attributes.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public AttributeDecl put( final AttributeDecl value ) {
        final String key = value.getName();

        @Nullable final AttributeDecl previous = m_attributes.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( "An attribute with name '"
                                    + key
                                    + "' is already defined in relation '"
                                    + m_name
                                    + '\'' );
        }

        return value;
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
