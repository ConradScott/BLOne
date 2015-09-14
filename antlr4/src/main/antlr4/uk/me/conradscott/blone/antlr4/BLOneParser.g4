parser grammar BLOneParser;

options {
    language = Java;
    tokenVocab = BLOneLexer;
}

// Program

program
    : statement*
    ;

// Statements

statement
    : relationDecl
    | ruleDecl
    | action
    ;

// TODO: Allow multiple relations?

relationDecl
    : '(' 'relation' Identifier documentationString? attributeDecl* ')'
    ;

ruleDecl
    : '(' 'rule' Identifier documentationString?  conditionElement+ '=>' action+ ')'
    ;

action
    : assertion
    | retraction
    ;

// TODO: Allow multiple relations?

assertion
    : '(' 'assert' tupleExpr ')'
    ;

// TODO: Allow multiple patterns?
// TODO: And do multiple patterns form a conjunction, so it only retracts those joins that match?

retraction
    : '(' 'retract' patternCE ')'
    ;

// Documentation strings

documentationString
    : StringLiteral+
    ;

// Attribute declarations

attributeDecl
    : '(' Identifier type ')'
    ;

// Conditional elements

conditionElement
    : capturedCE
    | simpleCE
    ;

capturedCE
    : Variable '=' patternCE
    ;

simpleCE
    : patternCE
    | compoundCE
    ;

patternCE
    : '(' Identifier attributeConstraint* ')'
    ;

compoundCE
    : '(' 'not' conditionElement ')'        # notCE
    | '(' 'and' conditionElement+ ')'       # andCE
    | '(' 'or' conditionElement+ ')'        # orCE
    | '(' 'exists' conditionElement+ ')'    # existsCE

    | '(' 'forall' rangeCE = conditionElement predicateCEs += conditionElement+ ')'
                                            # forallCE
    ;

// Constraints

attributeConstraint
    : capturedAttributeConstraint
    | simpleAttributeConstraint
    ;

capturedAttributeConstraint
    : Variable '=' simpleAttributeConstraint
    ;

simpleAttributeConstraint
    : '(' Identifier constraint ')'
    ;

constraint
    : expression                # expressionConstraint
    | '(' 'not' constraint ')'  # notConstraint
    | '(' 'and' constraint+ ')' # andConstraint
    | '(' 'or' constraint+ ')'  # orConstraint
    ;

// Expressions

tupleExpr
    : '(' Identifier attributeExpr* ')'
    ;

attributeExpr
    : '(' Identifier expression ')'
    ;

expression
    : literal   # LiteralExpression
    | Variable  # VariableExpression
    ;

// Types

type
    : primitiveType
    ;

primitiveType
    : 'boolean'
    | 'byte'
    | 'short'
    | 'int'
    | 'long'
    | 'float'
    | 'double'
    | 'char'
    | 'string'
    ;

// Literals

literal
    : booleanLiteral                # Boolean
    | IntegerLiteral                # Integer
    | LongIntegerLiteral            # LongInteger
    | FloatingPointLiteral          # FloatingPoint
    | DoubleFloatingPointLiteral    # DoubleFloatingPoint
    | CharacterLiteral              # Character
    | StringLiteral                 # String
    ;

booleanLiteral returns [boolean value]
    : 'true'    { $value = true; }
    | 'false'   { $value = false; }
    ;
