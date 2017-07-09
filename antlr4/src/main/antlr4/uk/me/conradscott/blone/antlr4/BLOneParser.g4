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
    : 'relation' INDENT Identifier documentationString? attributeDecl* DEDENT
    ;

ruleDecl
    : 'rule' INDENT Identifier documentationString?  conditionElement+ '=>' action+ DEDENT
    ;

action
    : assertion
    | retraction
    | modification
    | println
    ;

assertion
    : 'assert' INDENT tupleExpr DEDENT
    ;

retraction
    : 'retract' INDENT relationExpr DEDENT
    ;

modification
    : 'modify' INDENT relationExpr attributeExpr+ DEDENT
    ;

println
    : 'println' INDENT expression DEDENT
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

// TODO: We should also allow relation expressions and literals here (somewhat like patternCE's).

relationExpr
    : Variable
    ;

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
