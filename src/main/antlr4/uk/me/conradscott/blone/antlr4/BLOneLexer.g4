lexer grammar BLOneLexer;

options {
    language = Java;
}

// Reserved words

BOOLEAN     : 'boolean' ;
BYTE        : 'byte' ;
SHORT       : 'short' ;
INT         : 'int' ;
LONG        : 'long' ;
FLOAT       : 'float' ;
DOUBLE      : 'double' ;
CHAR        : 'char' ;
STRING      : 'string' ;

AND         : 'and' ;
ASSERT      : 'assert' ;
EXISTS      : 'exists' ;
FORALL      : 'forall' ;
NOT         : 'not' ;
OR          : 'or' ;
RELATION    : 'relation' ;
RETRACT     : 'retract' ;
RULE        : 'rule' ;

// §3.10.1 Integer Literals

IntegerLiteral
    :   DecimalIntegerLiteral
    |   HexIntegerLiteral
    |   OctalIntegerLiteral
    |   BinaryIntegerLiteral
    ;

fragment
DecimalIntegerLiteral
    :   DecimalNumeral IntegerTypeSuffix?
    ;

fragment
HexIntegerLiteral
    :   HexNumeral IntegerTypeSuffix?
    ;

fragment
OctalIntegerLiteral
    :   OctalNumeral IntegerTypeSuffix?
    ;

fragment
BinaryIntegerLiteral
    :   BinaryNumeral IntegerTypeSuffix?
    ;

fragment
IntegerTypeSuffix
    :   [lL]
    ;

fragment
DecimalNumeral
    :   '0'
    |   NonZeroDigit (Digits? | Underscores Digits)
    ;

fragment
Digits
    :   Digit (DigitsAndUnderscores? Digit)?
    ;

fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

fragment
DigitsAndUnderscores
    :   DigitOrUnderscore+
    ;

fragment
DigitOrUnderscore
    :   Digit
    |   '_'
    ;

fragment
Underscores
    :   '_'+
    ;

fragment
HexNumeral
    :   '0' [xX] HexDigits
    ;

fragment
HexDigits
    :   HexDigit (HexDigitsAndUnderscores? HexDigit)?
    ;

fragment
HexDigit
    :   [0-9a-fA-F]
    ;

fragment
HexDigitsAndUnderscores
    :   HexDigitOrUnderscore+
    ;

fragment
HexDigitOrUnderscore
    :   HexDigit
    |   '_'
    ;

fragment
OctalNumeral
    :   '0' Underscores? OctalDigits
    ;

fragment
OctalDigits
    :   OctalDigit (OctalDigitsAndUnderscores? OctalDigit)?
    ;

fragment
OctalDigit
    :   [0-7]
    ;

fragment
OctalDigitsAndUnderscores
    :   OctalDigitOrUnderscore+
    ;

fragment
OctalDigitOrUnderscore
    :   OctalDigit
    |   '_'
    ;

fragment
BinaryNumeral
    :   '0' [bB] BinaryDigits
    ;

fragment
BinaryDigits
    :   BinaryDigit (BinaryDigitsAndUnderscores? BinaryDigit)?
    ;

fragment
BinaryDigit
    :   [01]
    ;

fragment
BinaryDigitsAndUnderscores
    :   BinaryDigitOrUnderscore+
    ;

fragment
BinaryDigitOrUnderscore
    :   BinaryDigit
    |   '_'
    ;

// §3.10.2 Floating-Point Literals

FloatingPointLiteral
    :   DecimalFloatingPointLiteral
    |   HexadecimalFloatingPointLiteral
    ;

fragment
DecimalFloatingPointLiteral
    :   Digits '.' Digits? ExponentPart? FloatTypeSuffix?
    |   '.' Digits ExponentPart? FloatTypeSuffix?
    |   Digits ExponentPart FloatTypeSuffix?
    |   Digits FloatTypeSuffix
    ;

fragment
ExponentPart
    :   ExponentIndicator SignedInteger
    ;

fragment
ExponentIndicator
    :   [eE]
    ;

fragment
SignedInteger
    :   Sign? Digits
    ;

fragment
Sign
    :   [+-]
    ;

fragment
FloatTypeSuffix
    :   [fFdD]
    ;

fragment
HexadecimalFloatingPointLiteral
    :   HexSignificand BinaryExponent FloatTypeSuffix?
    ;

fragment
HexSignificand
    :   HexNumeral '.'?
    |   '0' [xX] HexDigits? '.' HexDigits
    ;

fragment
BinaryExponent
    :   BinaryExponentIndicator SignedInteger
    ;

fragment
BinaryExponentIndicator
    :   [pP]
    ;

// §3.10.3 Boolean Literals

BooleanLiteral
    :	'true'
    |	'false'
    ;

// §3.10.4 Character Literals

CharacterLiteral
    :	'\'' SingleCharacter '\''
    |	'\'' EscapeSequence '\''
    ;

fragment
SingleCharacter
    :	~['\\]
    ;

// §3.10.5 String Literals

StringLiteral
    :	'"' StringCharacters? '"'
    ;

fragment
StringCharacters
    :	StringCharacter+
    ;

fragment
StringCharacter
    :	~["\\]
    |	EscapeSequence
    ;

// §3.10.6 Escape Sequences for Character and String Literals

fragment
EscapeSequence
    :	'\\' [btnfr"'\\]
    |	OctalEscape
    ;

fragment
OctalEscape
    :	'\\' OctalDigit
    |	'\\' OctalDigit OctalDigit
    |	'\\' ZeroToThree OctalDigit OctalDigit
    ;

fragment
ZeroToThree
    :	[0-3]
    ;

// §3.10.7 The Null Literal

NullLiteral
    :	'null'
    ;

// §3.11 Separators

LPAREN  : '(';
RPAREN  : ')';
//LBRACE  : '{';
//RBRACE  : '}';
//LBRACK  : '[';
//RBRACK  : ']';
//SEMI    : ';';
//COMMA   : ',';
//DOT     : '.';

// §3.12 Operators

ASSIGN      : '=';
GT          : '>';
LT          : '<';
//BANG        : '!';
//TILDE       : '~';
QUESTION    : '?';
//COLON       : ':';
EQUAL       : '==';
LE          : '<=';
GE          : '>=';
NOTEQUAL    : '!=';
//AND         : '&&';
//OR          : '||';
//INC         : '++';
//DEC         : '--';
ADD         : '+';
SUB         : '-';
MUL         : '*';
DIV         : '/';
BITAND      : '&';
BITOR       : '|';
XOR         : '^';
MOD         : '%';
HASHROCKET  : '=>';

// §3.8 Identifiers (must appear after all keywords in the grammar)

Symbol
    :   ':' Identifier
    ;

Variable
    :   '?' Identifier
    ;

Identifier
    :   Letter LetterOrDigit*
    ;

fragment
Letter
    :   // these are the "letters" below 0xFF
        [a-zA-Z$_]
    |   // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        { Character.isJavaIdentifierStart(_input.LA(-1)) }?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        { Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
    ;

fragment
LetterOrDigit
    :   // these are the "letters or digits" below 0xFF
        [a-zA-Z0-9$_]
    |   // covers all characters above 0xFF which are not a surrogate
        ~[\u0000-\u00FF\uD800-\uDBFF]
        { Character.isJavaIdentifierPart(_input.LA(-1)) }?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        { Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
    ;

// Whitespace and comments

WS
    :   [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;
