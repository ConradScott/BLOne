package uk.me.conradscott.blone.compiler.typechecker;

import uk.me.conradscott.blone.ast.constraint.AttributeConstraint;
import uk.me.conradscott.blone.ast.type.RelationDecl;
import uk.me.conradscott.blone.compiler.ErrorCollectorIfc;

import java.util.stream.StreamSupport;

final class AttributeConstraintTypeChecker {
    private AttributeConstraintTypeChecker() {}

    static SymbolTable typecheck( final ErrorCollectorIfc errorCollector,
                                  final Iterable< AttributeConstraint > attributeConstraints,
                                  final SymbolTable symbolTable,
                                  final RelationDecl relationDecl )
    {
        return StreamSupport.stream( attributeConstraints.spliterator(), false )
                            .reduce( symbolTable,
                                     ( SymbolTable previous, AttributeConstraint ac ) -> typecheck( errorCollector,
                                                                                                    ac,
                                                                                                    previous,
                                                                                                    relationDecl ),
                                     ( SymbolTable previous, SymbolTable current ) -> current );
    }

    static SymbolTable typecheck( final ErrorCollectorIfc errorCollector,
                                  final AttributeConstraint attributeConstraint,
                                  final SymbolTable symbolTable,
                                  final RelationDecl relationDecl )
    {
        // TODO: relationDecl
        return ConstraintTypeChecker.typecheck( errorCollector, attributeConstraint.getConstraint(), symbolTable );
    }
}
