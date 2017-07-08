package uk.me.conradscott.blone.ast.scope;

import javax.annotation.Nullable;
import java.util.Iterator;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.impl.factory.Maps;

import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.type.RelationDecl;

final class RelationScope implements ScopeIfc< RelationDecl > {
    private static final RelationScope EMPTY = new RelationScope( Maps.immutable.empty() );

    private final ImmutableMap< String, RelationDecl > m_relationDecls;

    static RelationScope empty() {
        return EMPTY;
    }

    private RelationScope( final ImmutableMap< String, RelationDecl > relationDecls ) {
        m_relationDecls = relationDecls;
    }

    @Nullable @Override public RelationDecl get( final String key ) {
        @Nullable final RelationDecl value = m_relationDecls.get( key );

        assert ( value == null ) || value.getName().equals( key );

        return value;
    }

    @Override public RelationScope put( final RelationDecl value ) {
        final String key = value.getName();

        @Nullable final LocatedIfc previous = get( key );

        if ( previous != null ) {
            throw new ASTException( value.getLocation(),
                                    "A relation with name '"
                                    + key
                                    + "' is already defined at "
                                    + previous.getLocation() );
        }

        return new RelationScope( m_relationDecls.newWithKeyValue( key, value ) );
    }

    @Override public RichIterable< RelationDecl > values() {
        return m_relationDecls.valuesView();
    }

    @Override public Iterator< RelationDecl > iterator() {
        return m_relationDecls.iterator();
    }
}
