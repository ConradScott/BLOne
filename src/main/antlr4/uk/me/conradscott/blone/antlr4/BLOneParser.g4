parser grammar BLOneParser;

options {
    language = Java;
    tokenVocab = BLOneLexer;
}

// Program

program
    :   statement*
    ;

// Statements

statement
    :   relationDecl
    |   ruleDecl
    |   action
    ;

// TODO: Allow multiple relations?

relationDecl
    :   '(' 'relation' Identifier documentationString? attributeDecl* ')'
    ;

ruleDecl
    :   '(' 'rule' Identifier documentationString?  conditionElement+ '=>' action+ ')'
    ;

action
    :   assertion
    |   retraction
    ;

// TODO: Allow multiple relations?

assertion
    :   '(' 'assert' relationExpr ')'
    ;

// TODO: Allow multiple patterns?

retraction
    :   '(' 'retract' patternCE ')'
    ;

// Documentation strings

documentationString
    :   StringLiteral+
    ;

// Attribute declarations

attributeDecl
    :   '(' Identifier type ')'
    ;

// Expressions

relationExpr
    :   '(' Identifier attributeExpr* ')'
    ;

attributeExpr
    :   '(' Identifier expression ')'
    ;

expression
    :   literal
    |   Variable
    ;

// Conditional elements

conditionElement
    :   patternCE
    |   assignedPatternCE
    |   notCE
    |   andCE
    |   orCE
    |   existsCE
    |   forallCE
    ;

patternCE
    :   '(' Identifier attributeConstraint* ')'
    ;

assignedPatternCE
    :   Variable '=' conditionElement
    ;

notCE
    :   '(' 'not' conditionElement ')'
    ;

andCE
    :   '(' 'and' conditionElement+ ')'
    ;

orCE
    :   '(' 'or' conditionElement+ ')'
    ;

existsCE
    :   '(' 'exists' conditionElement+ ')'
    ;

forallCE
    :   '(' 'forall' rangeCE = conditionElement predicateCEs += conditionElement+ ')'
    ;

// Constraints

attributeConstraint
    :   '(' Identifier ( Variable '=' )? constraint ')'
    ;

constraint
    : '?'
    | literal
    | Variable
    | compoundConstraint
    ;

compoundConstraint
    : '(' 'not' constraint ')'
    | '(' 'and' constraint+ ')'
    | '(' 'or' constraint+ ')'
    ;

// Types

type
    :   primitiveType
    ;

primitiveType
    :   'boolean'
    |   'byte'
    |   'short'
    |   'int'
    |   'long'
    |   'float'
    |   'double'
    |   'char'
    |   'string'
    ;

// Literals

literal
    :   primitiveLiteral
    ;

primitiveLiteral
    :   Identifier
    |   BooleanLiteral
    |   IntegerLiteral
    |   LongIntegerLiteral
    |   FloatingPointLiteral
    |   DoubleFloatingPointLiteral
    |   CharacterLiteral
    |   StringLiteral
    ;
