package uk.me.conradscott.blone.ast;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class PatternCE implements Iterable<AttributePattern>, ConditionElementIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final Map<String, AttributePattern> m_attributes = Maps.newLinkedHashMap();

    public PatternCE(@NotNull final LocationIfc location, @NotNull final String name) {
        m_location = location;
        m_name = name;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @Nullable public AttributePattern put(@NotNull final AttributePattern attributePattern) {
        final String name = attributePattern.getName();

        final AttributePattern previous = m_attributes.putIfAbsent(name, attributePattern);

        if (previous != null) {
            assert previous.getName().equals(name);

            throw new ASTException(attributePattern.getLocation()
                                   + ": an attribute with name '"
                                   + name
                                   + "' is already defined in relation '"
                                   + m_name
                                   + '\'');
        }

        return attributePattern;
    }

    @Nullable public AttributePattern get(@NotNull final String name) {
        final AttributePattern literal = m_attributes.get(name);

        if (literal == null) {
            throw new ASTException("No attribute with name '" + name + "' has been defined");
        }

        assert literal.getName().equals(name);

        return literal;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @Override public Iterator<AttributePattern> iterator() {
        return m_attributes.values().iterator();
    }

    @Override public void forEach(final Consumer<? super AttributePattern> action) {
        m_attributes.values().forEach(action);
    }

    @Override public Spliterator<AttributePattern> spliterator() {
        return m_attributes.values().spliterator();
    }
}
