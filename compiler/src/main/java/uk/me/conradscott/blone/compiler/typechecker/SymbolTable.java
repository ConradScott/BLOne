package uk.me.conradscott.blone.compiler.typechecker;

import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import uk.me.conradscott.blone.ast.ASTException;
import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.scope.ScopeIfc;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

final class SymbolTable implements ScopeIfc< String, Variable > {
    /**
     * An immutable empty symbol table to use as the ultimate parent of all symbol table stacks.
     */
    private static final class Root implements ScopeIfc< String, Variable > {
        @Nullable @Override public Variable get( final String key ) {
            return null;
        }

        @Override public Variable put( final Variable value ) {
            throw new UnsupportedOperationException( "Insertion is not supported on the root symbol table." );
        }

        @Override public Iterator< Variable > iterator() {
            return Collections.emptyIterator();
        }
    }

    private static final Root ROOT = new Root();

    private final ScopeIfc< String, Variable > m_parent;
    private final Map< String, Variable > m_map = Maps.newLinkedHashMap();

    SymbolTable() {
        m_parent = ROOT;
    }

    SymbolTable( final SymbolTable parent ) {
        m_parent = parent;
    }

    @Override @Nullable public Variable get( final String key ) {
        @Nullable final Variable value = m_map.get( key );

        if ( value != null ) {
            assert value.getName().equals( key );
            return value;
        }

        return m_parent.get( key );
    }

    @Nullable public Variable get( final Variable key ) {
        return get( key.getName() );
    }

    @Override public Variable put( final Variable value ) {
        @Nullable final Variable previous = get( value );

        if ( previous != null ) {
            throw new ASTException( "A variable with name '"
                                    + value.getName()
                                    + "' is already defined at "
                                    + previous.getLocation() );
        }

        m_map.put( value.getName(), value );

        return value;
    }

    @Override public Iterator< Variable > iterator() {
        return Iterators.concat( m_parent.iterator(), m_map.values().iterator() );
    }
}
