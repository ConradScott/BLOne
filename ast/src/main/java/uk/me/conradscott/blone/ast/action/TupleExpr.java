package uk.me.conradscott.blone.ast.action;

import com.gs.collections.api.RichIterable;
import com.gs.collections.api.map.ImmutableMap;
import com.gs.collections.impl.factory.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import javax.annotation.Nullable;

public final class TupleExpr implements ScopeIfc< AttributeExpr >, LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final ImmutableMap< String, AttributeExpr > m_attributes;

    public TupleExpr( final LocationIfc location, final String name ) {
        this( location, name, Maps.immutable.empty() );
    }

    private TupleExpr( final LocationIfc location,
                       final String name,
                       final ImmutableMap< String, AttributeExpr > attributes )
    {
        m_location = location;
        m_name = name;
        m_attributes = attributes;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    @Override public AttributeExpr get( final String key ) {
        @Nullable final AttributeExpr value = m_attributes.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public TupleExpr put( final AttributeExpr value ) {
        final String key = value.getName();

        @Nullable final AttributeExpr previous = get( key );

        if ( previous != null ) {
            throw new ASTException( value.getLocation(),
                                    "A value for the attribute '"
                                    + key
                                    + "' has already been given in the expression for '"
                                    + m_name
                                    + '\'' );
        }

        return new TupleExpr( m_location, m_name, m_attributes.newWithKeyValue( key, value ) );
    }

    @Override public RichIterable< AttributeExpr > values() {
        return m_attributes.valuesView();
    }

    @Override public Iterator< AttributeExpr > iterator() {
        return m_attributes.iterator();
    }
}
