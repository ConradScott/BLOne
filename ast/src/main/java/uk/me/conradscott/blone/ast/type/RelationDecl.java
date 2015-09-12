package uk.me.conradscott.blone.ast.type;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.declaration.AttributeDecl;
import uk.me.conradscott.blone.ast.declaration.DeclarationIfc;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public final class RelationDecl implements DeclarationIfc, TypeIfc, ScopeIfc< AttributeDecl > {
    private final LocationIfc m_location;
    private final IdentifierIfc m_identifier;
    @Nullable private final StringLiteral m_documentationString;
    private final Map< String, AttributeDecl > m_attributes = Maps.newHashMap();

    public RelationDecl( final LocationIfc location,
                         final IdentifierIfc identifier,
                         @Nullable final StringLiteral documentationString )
    {
        m_location = location;
        m_identifier = identifier;
        m_documentationString = documentationString;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public IdentifierIfc getIdentifier() {
        return m_identifier;
    }

    @Override public String getName() {
        return m_identifier.getName();
    }

    @Override public TypeIfc getType() {
        return this;
    }

    @Nullable public StringLiteral getDocumentationString() {
        return m_documentationString;
    }

    @Nullable @Override public AttributeDecl get( final String key ) {
        return m_attributes.get( key );
    }

    @Override public AttributeDecl put( final AttributeDecl value ) {
        return m_attributes.put( value.getName(), value );
    }

    @Override public Stream< AttributeDecl > stream() {
        return m_attributes.values().stream();
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

    @Override public < T, R > R accept( final TypeVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
