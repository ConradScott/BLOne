package uk.me.conradscott.blone.ast.declaration;

import com.gs.collections.api.RichIterable;
import com.gs.collections.api.map.ImmutableMap;
import com.gs.collections.impl.factory.Lists;
import com.gs.collections.impl.factory.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Iterator;
import javax.annotation.Nullable;

public final class SymbolTable implements ScopeIfc< VariableDecl > {
    /**
     * An empty symbol table to use as the ultimate parent of all symbol table stacks.
     */
    private static final ScopeIfc< VariableDecl > ROOT = new ScopeIfc< VariableDecl >() {

        @Nullable @Override public VariableDecl get( final String key ) {
            return null;
        }

        @Override public ScopeIfc< VariableDecl > put( final VariableDecl value ) {
            throw new UnsupportedOperationException( "Insertion is not supported on the root symbol table." );
        }

        @Override public RichIterable< VariableDecl > values() {
            return Lists.immutable.empty();
        }

        @Override public Iterator< VariableDecl > iterator() {
            return Lists.immutable.< VariableDecl > empty().iterator();
        }
    };

    private final ScopeIfc< VariableDecl > m_parent;
    private final ImmutableMap< String, VariableDecl > m_map;

    public SymbolTable() {
        this( ROOT, Maps.immutable.empty() );
    }

    public SymbolTable( final SymbolTable parent ) {
        this( parent, Maps.immutable.empty() );
    }

    private SymbolTable( final ScopeIfc< VariableDecl > parent, final ImmutableMap< String, VariableDecl > map ) {
        m_parent = parent;
        m_map = map;
    }

    @Override public VariableDecl get( final String key ) {
        @Nullable final VariableDecl value = m_map.get( key );

        if ( value != null ) {
            assert value.getName().equals( key );
            return value;
        }

        return m_parent.get( key );
    }

    @Nullable public VariableDecl get( final VariableDecl key ) {
        return get( key.getIdentifier().getName() );
    }

    @Override public SymbolTable put( final VariableDecl value ) {
        final String key = value.getIdentifier().getName();

        @Nullable final DeclarationIfc previous = get( key );

        if ( previous != null ) {
            assert previous.getName().equals( key );

            throw new ASTException( value.getLocation(),
                                    "A variable with name '"
                                    + key
                                    + "' is already defined at "
                                    + previous.getIdentifier().getLocation() );
        }

        return new SymbolTable( m_parent, m_map.newWithKeyValue( key, value ) );
    }

    @Override public RichIterable< VariableDecl > values() {
        return Lists.immutable.withAll( m_parent.values() ).newWithAll( m_map.valuesView() );
    }

    @Override public Iterator< VariableDecl > iterator() {
        return m_map.iterator();
    }
}
