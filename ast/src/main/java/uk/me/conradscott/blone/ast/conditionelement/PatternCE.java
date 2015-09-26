package uk.me.conradscott.blone.ast.conditionelement;

import com.gs.collections.api.RichIterable;
import com.gs.collections.api.map.ImmutableMap;
import com.gs.collections.impl.factory.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.attributeconstraint.AttributeConstraintIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import javax.annotation.Nullable;

public final class PatternCE implements ScopeIfc< AttributeConstraintIfc >, ConditionElementIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final ImmutableMap< String, AttributeConstraintIfc > m_attributes;

    public PatternCE( final LocationIfc location, final String name ) {
        this( location, name, Maps.immutable.empty() );
    }

    private PatternCE( final LocationIfc location,
                       final String name,
                       final ImmutableMap< String, AttributeConstraintIfc > attributes )
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

    @Nullable @Override public AttributeConstraintIfc get( final String key ) {
        @Nullable final AttributeConstraintIfc value = m_attributes.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public PatternCE put( final AttributeConstraintIfc value ) {
        final String key = value.getName();

        @Nullable final AttributeConstraintIfc previous = get( key );

        if ( previous != null ) {
            throw new ASTException( value.getLocation(),
                                    "A constraint for the attribute '"
                                    + key
                                    + "' has already been given in the pattern for '"
                                    + m_name
                                    + '\'' );
        }

        return new PatternCE( m_location, m_name, m_attributes.newWithKeyValue( key, value ) );
    }

    @Override public RichIterable< AttributeConstraintIfc > values() {
        return m_attributes.valuesView();
    }

    @Override public Iterator< AttributeConstraintIfc > iterator() {
        return m_attributes.iterator();
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
