package uk.me.conradscott.blone.ast.rule;

import com.gs.collections.api.RichIterable;
import uk.me.conradscott.blone.ast.action.ActionIfc;
import uk.me.conradscott.blone.ast.conditionelement.ConditionElementIfc;
import uk.me.conradscott.blone.ast.literal.StringLiteral;
import uk.me.conradscott.blone.ast.location.LocatedIfc;
import uk.me.conradscott.blone.ast.location.LocationIfc;

import javax.annotation.Nullable;

public final class RuleDecl implements LocatedIfc {
    private final LocationIfc m_location;
    private final String m_name;
    @Nullable private final StringLiteral m_documentationString;
    private final ConditionElementIfc m_conditionElement;
    private final RichIterable< ActionIfc > m_actions;

    public RuleDecl( final LocationIfc location,
                     final String name,
                     @Nullable final StringLiteral documentationString,
                     final ConditionElementIfc conditionElement,
                     final RichIterable< ActionIfc > actions )
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

    @Nullable public StringLiteral getDocumentationString() {
        return m_documentationString;
    }

    public ConditionElementIfc getConditionElement() {
        return m_conditionElement;
    }

    public RichIterable< ActionIfc > getActions() {
        return m_actions;
    }
}
