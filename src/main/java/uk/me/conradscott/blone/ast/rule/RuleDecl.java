package uk.me.conradscott.blone.ast.rule;

import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

public final class RuleDecl implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    @Nullable private final StringLiteral m_documentationString;
    private final ConditionElementIfc m_conditionElement;
    private final List< ActionIfc > m_actions;

    public RuleDecl( final LocationIfc location,
                     final String name,
                     @Nullable final StringLiteral documentationString,
                     final ConditionElementIfc conditionElement,
                     final List< ActionIfc > actions )
    {
        m_location = location;
        m_name = name;
        m_documentationString = documentationString;
        m_conditionElement = conditionElement;
        m_actions = actions;
    }

    @Override public LocationIfc getLocation() {
        return m_location;
    }

    public String getName() {
        return m_name;
    }

    public Optional< StringLiteral > getDocumentationString() {
        return Optional.ofNullable( m_documentationString );
    }

    public ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }

    public List< ActionIfc > getActions() {
        return m_actions;
    }
}
