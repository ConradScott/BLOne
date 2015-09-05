package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.constraint.ConjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.constraint.ConstraintVisitorIfc;
import uk.me.conradscott.blone.ast.constraint.DisjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ExpressionConstraint;
import uk.me.conradscott.blone.ast.constraint.NegativeConstraint;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class ConstraintTypeChecker {
    private ConstraintTypeChecker() {}

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final ConstraintIfc ce,
                              final SymbolTable symbolTable )
    {
        return new Visitor( errorCollector ).visit( ce, symbolTable );
    }

    private static final class Visitor implements ConstraintVisitorIfc< SymbolTable, SymbolTable > {
        private final ErrorCollectorIfc m_errorCollector;

        private Visitor( final ErrorCollectorIfc errorCollector ) {
            m_errorCollector = errorCollector;
        }

        @Override public SymbolTable visit( final ExpressionConstraint constraint, final SymbolTable symbolTable ) {
            return null;
        }

        @Override public SymbolTable visit( final NegativeConstraint constraint, final SymbolTable symbolTable ) {
            return null;
        }

        @Override public SymbolTable visit( final ConjunctiveConstraint constraint, final SymbolTable symbolTable ) {
            return null;
        }

        @Override public SymbolTable visit( final DisjunctiveConstraint constraint, final SymbolTable symbolTable ) {
            return null;
        }
    }
}
