package uk.me.conradscott.blone.ast.conditionelement;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.constraint.AttributeConstraint;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public final class PatternCE implements ScopeIfc< String, AttributeConstraint >, ConditionElementIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final Map< String, AttributeConstraint > m_attributes = Maps.newLinkedHashMap();

    public PatternCE( final LocationIfc location, final String name ) {
        m_location = location;
        m_name = name;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    @Nullable @Override public AttributeConstraint get( final String key ) {
        @Nullable final AttributeConstraint value = m_attributes.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public AttributeConstraint put( final AttributeConstraint value ) {
        final String key = value.getName();

        @Nullable final AttributeConstraint previous = m_attributes.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( "A constraint for the attribute '"
                                    + key
                                    + "' has already been given in the pattern for '"
                                    + m_name
                                    + '\'' );
        }

        return value;
    }

    @Override public Iterator< AttributeConstraint > iterator() {
        return m_attributes.values().iterator();
    }

    @Override public void forEach( final Consumer< ? super AttributeConstraint > action ) {
        m_attributes.values().forEach( action );
    }

    @Override public Spliterator< AttributeConstraint > spliterator() {
        return m_attributes.values().spliterator();
    }

    @Override public < T, R > R accept( final ConditionElementVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }
}
