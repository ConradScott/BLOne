package uk.me.conradscott.blone.ast.statement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;

import java.util.Optional;

public final class RuleDecl implements LocatedIfc {
    @NotNull private final LocationIfc m_location;
    @NotNull private final String m_name;
    @Nullable private final StringLiteral m_documentationString;
    @NotNull private final ConditionElementIfc m_conditionElement;

    public RuleDecl( @NotNull final LocationIfc location,
                     @NotNull final String name,
                     @Nullable final StringLiteral documentationString,
                     @NotNull final ConditionElementIfc conditionElement )
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

    @NotNull public Optional< StringLiteral > getDocumentationString() {
        return Optional.ofNullable( m_documentationString );
    }

    @NotNull public ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }
}
