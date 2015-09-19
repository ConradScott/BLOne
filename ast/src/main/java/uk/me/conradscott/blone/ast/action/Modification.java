package uk.me.conradscott.blone.ast.action;

import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public final class Modification implements ActionIfc, ScopeIfc< AttributeExpr > {
    private final LocationIfc m_location;
    private final Variable m_variable;
    private final Map< String, AttributeExpr > m_attributes = Maps.newLinkedHashMap();

    public Modification( final LocationIfc location, final Variable variable ) {
        m_location = location;
        m_variable = variable;
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

    @Override public AttributeExpr put( final AttributeExpr value ) {
        final String key = value.getName();

        @Nullable final AttributeExpr previous = m_attributes.putIfAbsent( key, value );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( "A value for the attribute '"
                                    + key
                                    + "' has already been given in the modification for '"
                                    + m_variable.getName()
                                    + '\'' );
        }

        return value;
    }

    @Override public < T, R > R accept( final ActionVisitorIfc< T, R > visitor, final T t ) {
        return visitor.visit( this, t );
    }

    @Override public Stream< AttributeExpr > stream() {
        return m_attributes.values().stream();
    }

    @Override public Iterator< AttributeExpr > iterator() {
        return m_attributes.values().iterator();
    }

    @Override public void forEach( final Consumer< ? super AttributeExpr > action ) {
        m_attributes.values().forEach( action );
    }

    @Override public Spliterator< AttributeExpr > spliterator() {
        return m_attributes.values().spliterator();
    }
}
