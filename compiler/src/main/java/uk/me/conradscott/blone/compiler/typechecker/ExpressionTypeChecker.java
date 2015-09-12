package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.declaration.SymbolTable;
import uk.me.conradscott.blone.ast.expression.ExpressionIfc;
import uk.me.conradscott.blone.ast.expression.ExpressionVisitorIfc;
import uk.me.conradscott.blone.ast.expression.Variable;
import uk.me.conradscott.blone.ast.literal.PrimitiveLiteralIfc;
import uk.me.conradscott.blone.ast.type.TypeIfc;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

final class ExpressionTypeChecker {
    private ExpressionTypeChecker() {}

    /**
     * Check that the given expression is compatible with the given type.
     *
     * @param errorCollector
     * @param expression
     * @param symbolTable
     * @param type
     */
    static SymbolTable check( final ErrorCollectorIfc errorCollector,
                              final ExpressionIfc expression,
                              final SymbolTable symbolTable,
                              final TypeIfc type )
    {
        return new Visitor( errorCollector, type ).visit( expression, symbolTable );
    }

    private static final class Visitor implements ExpressionVisitorIfc< SymbolTable, SymbolTable > {
        private final ErrorCollectorIfc m_errorCollector;
        private final TypeIfc m_type;

        private Visitor( final ErrorCollectorIfc errorCollector, final TypeIfc type ) {
            m_errorCollector = errorCollector;
            m_type = type;
        }

        @Override public SymbolTable visit( final PrimitiveLiteralIfc< ? > literal, final SymbolTable symbolTable ) {
            TypeCompatibilityChecker.check( m_errorCollector, m_type, literal );

            return symbolTable;
        }

        @Override public SymbolTable visit( final Variable variable, final SymbolTable symbolTable ) {
            VariableTypeChecker.check( m_errorCollector, variable, symbolTable, m_type );

            return symbolTable;
        }
    }
}
