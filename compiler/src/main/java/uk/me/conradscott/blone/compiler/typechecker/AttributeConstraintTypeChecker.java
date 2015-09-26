package uk.me.conradscott.blone.compiler.typechecker;

import com.gs.collections.api.RichIterable;
import uk.me.conradscott.blone.ast.attributeconstraint.AttributeConstraintIfc;
import uk.me.conradscott.blone.ast.attributeconstraint.AttributeConstraintVisitorIfc;
import uk.me.conradscott.blone.ast.attributeconstraint.CapturedAttributeConstraint;
import uk.me.conradscott.blone.ast.attributeconstraint.SimpleAttributeConstraint;
import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class AttributeConstraintTypeChecker {
    private AttributeConstraintTypeChecker() {}

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final RichIterable< AttributeConstraintIfc > attributeConstraints,
                              final SymbolTable symbolTable,
                              final RelationDecl relationDecl )
    {
        return attributeConstraints.injectInto( symbolTable,
                                                ( state, ac ) -> check( errorCollector, ac, state, relationDecl ) );
    }

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final AttributeConstraintIfc attributeConstraint,
                              final SymbolTable symbolTable,
                              final RelationDecl relationDecl )
    {
        return new Visitor( errorCollector, relationDecl ).visit( attributeConstraint, symbolTable );
    }

    private static final class Visitor implements AttributeConstraintVisitorIfc< SymbolTable, SymbolTable > {
        private final ErrorCollectorIfc m_errorCollector;
        private final RelationDecl m_relationDecl;

        private Visitor( final ErrorCollectorIfc errorCollector, final RelationDecl relationDecl ) {
            m_errorCollector = errorCollector;
            m_relationDecl = relationDecl;
        }

        @Override
        public SymbolTable visit( final SimpleAttributeConstraint attributeConstraint, final SymbolTable symbolTable ) {
            return SimpleAttributeConstraintTypeChecker.check( m_errorCollector,
                                                               attributeConstraint,
                                                               symbolTable,
                                                               m_relationDecl );
        }

        @Override
        public SymbolTable visit( final CapturedAttributeConstraint attributeConstraint, final SymbolTable symbolTable )
        {
            return SimpleAttributeConstraintTypeChecker.check( m_errorCollector,
                                                               attributeConstraint.getCaptureVariable(),
                                                               attributeConstraint.getAttributeConstraint(),
                                                               symbolTable,
                                                               m_relationDecl );
        }
    }
}
