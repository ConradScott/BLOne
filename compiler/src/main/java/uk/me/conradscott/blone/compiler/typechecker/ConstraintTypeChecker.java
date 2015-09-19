package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.constraint.ConjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ConstraintIfc;
import uk.me.conradscott.blone.ast.constraint.ConstraintVisitorIfc;
import uk.me.conradscott.blone.ast.constraint.DisjunctiveConstraint;
import uk.me.conradscott.blone.ast.constraint.ExpressionConstraint;
import uk.me.conradscott.blone.ast.constraint.NegativeConstraint;
import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.type.PrimitiveType;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import static uk.me.conradscott.blone.hof.HOFs.foldl;

final class ConstraintTypeChecker {
    private ConstraintTypeChecker() {}

    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final ConstraintIfc constraint,
                              final SymbolTable symbolTable,
                              final PrimitiveType type )
    {
        return new Visitor( errorCollector, type ).visit( constraint, symbolTable );
    }

    private static final class Visitor implements ConstraintVisitorIfc< SymbolTable, SymbolTable > {
        private final ErrorCollectorIfc m_errorCollector;
        private final PrimitiveType m_type;

        private Visitor( final ErrorCollectorIfc errorCollector, final PrimitiveType type ) {
            m_errorCollector = errorCollector;
            m_type = type;
        }

        @Override public SymbolTable visit( final ExpressionConstraint constraint, final SymbolTable symbolTable ) {
            return ExpressionTypeChecker.check( m_errorCollector, constraint.getExpression(), symbolTable, m_type );
        }

        @Override public SymbolTable visit( final NegativeConstraint constraint, final SymbolTable symbolTable ) {
            return visit( constraint.getConstraint(), symbolTable );
        }

        @Override public SymbolTable visit( final ConjunctiveConstraint constraint, final SymbolTable symbolTable ) {
            return foldl( constraint.getConjuncts(), symbolTable, ( state, conjunct ) -> visit( conjunct, state ) );
        }

        @Override public SymbolTable visit( final DisjunctiveConstraint constraint, final SymbolTable symbolTable ) {
            return foldl( constraint.getDisjuncts(), symbolTable, ( state, disjunct ) -> visit( disjunct, state ) );
        }
    }
}
