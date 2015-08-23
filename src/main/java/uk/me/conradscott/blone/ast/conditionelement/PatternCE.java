package uk.me.conradscott.blone.ast.conditionelement;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;
import uk.me.conradscott.blone.ast.statement.AttributeConstraint;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class PatternCE implements ScopeIfc< String, AttributeConstraint >, ConditionElementIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @NotNull private final Map< String, AttributeConstraint > m_attributes = Maps.newLinkedHashMap();

    public PatternCE( @NotNull final LocationIfc location, @NotNull final String name ) {
        m_location = location;
        m_name = name;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @NotNull @Override public AttributeConstraint put( @NotNull final AttributeConstraint value ) {
        final String key = value.getName();

        final AttributeConstraint previous = m_attributes.putIfAbsent( key, value );

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

    @NotNull @Override public AttributeConstraint get( @NotNull final String key ) {
        final AttributeConstraint value = m_attributes.get( key );

        if ( value == null ) {
            throw new ASTException( "No attribute with name '" + key + "' has been defined" );
        }

        assert value.getName().equals( key );

        return value;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
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

    @Nullable @Override
    public < T, R > R accept( @NotNull final ConditionElementVisitorIfc< T, R > visitor, @NotNull final T t ) {
        return visitor.visitPatternCE( this, t );
    }
}
