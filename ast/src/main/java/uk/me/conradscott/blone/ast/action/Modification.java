package uk.me.conradscott.blone.ast.action;

import com.gs.collections.api.RichIterable;
import com.gs.collections.api.map.ImmutableMap;
import com.gs.collections.impl.factory.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import javax.annotation.Nullable;

public final class Modification implements ActionIfc, ScopeIfc< AttributeExpr > {
    private final LocationIfc m_location;
    private final Variable m_variable;
    private final ImmutableMap< String, AttributeExpr > m_attributes;

    public Modification( final LocationIfc location, final Variable variable ) {
        this( location, variable, Maps.immutable.empty() );
    }

    private Modification( final LocationIfc location,
                          final Variable variable,
                          final ImmutableMap< String, AttributeExpr > attributes )
    {
        m_location = location;
        m_variable = variable;
        m_attributes = attributes;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public Variable getVariable() {
        return m_variable;
    }

    @Nullable @Override public AttributeExpr get( final String key ) {
        @Nullable final AttributeExpr value = m_attributes.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public Modification put( final AttributeExpr value ) {
        final String key = value.getName();

        @Nullable final AttributeExpr previous = get( key );

        if ( previous != null ) {
            throw new ASTException( value.getLocation(),
                                    "A value for the attribute '"
                                    + key
                                    + "' has already been given in the modification for '"
                                    + m_variable.getName()
                                    + '\'' );
        }

        return new Modification( m_location, m_variable, m_attributes.newWithKeyValue( key, value ) );
    }

    @Override public RichIterable< AttributeExpr > values() {
        return m_attributes.valuesView();
    }

    @Override public < T, R > R accept( final ActionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }

    @Override public Iterator< AttributeExpr > iterator() {
        return m_attributes.iterator();
    }
}
