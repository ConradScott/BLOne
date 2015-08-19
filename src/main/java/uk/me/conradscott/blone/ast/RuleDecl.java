package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class RuleDecl implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    private final String m_documentationString;
    private final ConditionElementIfc m_conditionElement;

    public RuleDecl(@NotNull final LocationIfc location,
                    @NotNull final String name,
                    @Nullable final String documentationString,
                    @NotNull final ConditionElementIfc conditionElement)
    {
        m_location = location;
        m_name = name;
        m_documentationString = documentationString;
        m_conditionElement = conditionElement;
    }

    @NotNull @Override public LocationIfc getLocation() {
        return m_location;
    }

    @NotNull public String getName() {
        return m_name;
    }

    @Nullable public String getDocumentationString() {
        return m_documentationString;
    }

    @NotNull ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }
}
