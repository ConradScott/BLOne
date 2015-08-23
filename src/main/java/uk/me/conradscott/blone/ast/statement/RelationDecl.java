package uk.me.conradscott.blone.ast.statement;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RelationDecl implements ScopeIfc< String, AttributeDecl >, LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @Nullable private final StringLiteral m_documentationString;
    @NotNull private final Map< String, AttributeDecl > m_attributes = Maps.newLinkedHashMap();

    public RelationDecl( @NotNull final LocationIfc location,
                         @NotNull final String name,
                         @Nullable final StringLiteral documentationString )
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

    @NotNull public Optional< StringLiteral > getDocumentationString() {
        return Optional.ofNullable( m_documentationString );
    }

    @NotNull @Override public AttributeDecl put( @NotNull final AttributeDecl value ) {
        final String key = value.getName();

        final AttributeDecl previous = m_attributes.putIfAbsent( key, value );

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

    @NotNull @Override public AttributeDecl get( @NotNull final String key ) {
        final AttributeDecl value = m_attributes.get( key );

        if ( value == null ) {
            throw new ASTException( "No attribute with name '" + key + "' has been defined" );
        }

        assert value.getName().equals( key );

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
