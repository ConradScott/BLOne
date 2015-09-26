package uk.me.conradscott.blone.ast.type;

import com.gs.collections.api.RichIterable;
import com.gs.collections.api.map.ImmutableMap;
import com.gs.collections.impl.factory.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.declaration.AttributeDecl;
import uk.me.conradscott.blone.ast.declaration.DeclarationIfc;
import uk.me.conradscott.blone.ast.declaration.IdentifierIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import javax.annotation.Nullable;

public final class RelationDecl implements DeclarationIfc, TypeIfc, ScopeIfc< AttributeDecl > {
    private final LocationIfc m_location;
    private final IdentifierIfc m_identifier;
    @Nullable private final StringLiteral m_documentationString;
    private final ImmutableMap< String, AttributeDecl > m_attributes;

    public RelationDecl( final LocationIfc location,
                         final IdentifierIfc identifier,
                         @Nullable final StringLiteral documentationString )
    {
        this( location, identifier, documentationString, Maps.immutable.empty() );
    }

    private RelationDecl( final LocationIfc location,
                          final IdentifierIfc identifier,
                          @Nullable final StringLiteral documentationString,
                          final ImmutableMap< String, AttributeDecl > attributes )
    {
        m_location = location;
        m_identifier = identifier;
        m_documentationString = documentationString;
        m_attributes = attributes;
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
        @Nullable final AttributeDecl value = m_attributes.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public RelationDecl put( final AttributeDecl value ) {
        final String key = value.getName();

        @Nullable final AttributeDecl previous = get( key );

        if ( previous != null ) {
            throw new ASTException( value.getLocation(),
                                    "An attribute with name '"
                                    + key
                                    + "' has already been given in the declaration for relation '"
                                    + m_identifier.getName()
                                    + '\'' );
        }

        return new RelationDecl( m_location,
                                 m_identifier,
                                 m_documentationString,
                                 m_attributes.newWithKeyValue( key, value ) );
    }

    @Override public RichIterable< AttributeDecl > values() {
        return m_attributes.valuesView();
    }

    @Override public Iterator< AttributeDecl > iterator() {
        return m_attributes.iterator();
    }

    @Override public < T, R > R accept( final TypeVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
