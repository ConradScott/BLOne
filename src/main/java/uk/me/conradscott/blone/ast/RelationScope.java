package uk.me.conradscott.blone.ast;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RelationScope implements ScopeIfc<String, RelationDecl> {
    private final Map<String, RelationDecl> m_relationDecls = Maps.newLinkedHashMap();

    @Nullable @Override public RelationDecl put(@NotNull final RelationDecl value) {
        final String name = value.getName();

        final RelationDecl previous = m_relationDecls.putIfAbsent(name, value);

        if (previous != null) {
            assert previous.getName().equals(name);

            throw new ASTException(value.getLocation()
                                   + ": a relation with name '"
                                   + name
                                   + "' is already defined at "
                                   + previous.getLocation());
        }

        return value;
    }

    @Nullable @Override public RelationDecl get(@NotNull final String key) {
        final RelationDecl decl = m_relationDecls.get(key);

        if (decl == null) {
            throw new ASTException("No relation with name  '" + key + "' has been defined");
        }

        assert decl.getName().equals(key);

        return decl;
    }

    @Override public Iterator<RelationDecl> iterator() {
        return m_relationDecls.values().iterator();
    }

    @Override public void forEach(final Consumer<? super RelationDecl> action) {
        m_relationDecls.values().forEach(action);
    }

    @Override public Spliterator<RelationDecl> spliterator() {
        return m_relationDecls.values().spliterator();
    }
}
