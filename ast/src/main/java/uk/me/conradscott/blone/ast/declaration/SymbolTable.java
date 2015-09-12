package uk.me.conradscott.blone.ast.declaration;

import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public final class SymbolTable implements ScopeIfc< VariableDecl > {
    /**
     * An immutable empty symbol table to use as the ultimate parent of all symbol table stacks.
     */
    private static final class Root implements ScopeIfc< VariableDecl > {
        @Nullable @Override public VariableDecl get( final String key ) {
            return null;
        }

        @Override public VariableDecl put( final VariableDecl value ) {
            throw new UnsupportedOperationException( "Insertion is not supported on the root symbol table." );
        }

        @Override public Stream< VariableDecl > stream() {
            return Stream.empty();
        }

        @Override public Iterator< VariableDecl > iterator() {
            return Collections.emptyIterator();
        }
    }

    private final ScopeIfc< VariableDecl > m_parent;
    private final Map< String, VariableDecl > m_map;

    public SymbolTable() {
        m_parent = new Root();
        m_map = Maps.newLinkedHashMap();
    }

    public SymbolTable( final ScopeIfc< VariableDecl > parent ) {
        m_parent = parent;
        m_map = Maps.newLinkedHashMap();
    }

    public SymbolTable( final Map< String, VariableDecl > map ) {
        m_parent = new Root();
        m_map = map;
    }

    @Nullable @Override public VariableDecl get( final String key ) {
        @Nullable final VariableDecl value = m_map.get( key );

        if ( value != null ) {
            assert value.getIdentifier().getName().equals( key );
            return value;
        }

        return m_parent.get( key );
    }

    @Nullable public VariableDecl get( final VariableDecl key ) {
        return get( key.getIdentifier().getName() );
    }

    @Override public VariableDecl put( final VariableDecl value ) {
        final String key = value.getIdentifier().getName();

        @Nullable final DeclarationIfc previous = get( key );

        if ( previous != null ) {
            throw new ASTException( "A variable with name '"
                                    + key
                                    + "' is already defined at "
                                    + previous.getIdentifier().getLocation() );
        }

        m_map.put( key, value );

        return value;
    }

    @Override public Stream< VariableDecl > stream() {
        return Stream.concat( m_parent.stream(), m_map.values().stream() );
    }

    public SymbolTable orphan() {
        return ( m_parent instanceof Root ) ? this : new SymbolTable( m_map );
    }

    @Override public Iterator< VariableDecl > iterator() {
        return Iterators.concat( m_parent.iterator(), m_map.values().iterator() );
    }
}
