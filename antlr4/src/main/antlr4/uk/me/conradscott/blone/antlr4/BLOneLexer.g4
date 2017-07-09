lexer grammar BLOneLexer;

options {
    language = Java;
}

tokens { INDENT, DEDENT }

@lexer::header {
    import uk.me.conradscott.blone.dentation.Dentation;
}

@lexer::members {
    private final Dentation m_dentation =
        new Dentation( () -> super.nextToken(),
                       NL, INDENT, DEDENT,
                       new int[] { RELATION, RULE, ASSERT, RETRACT, MODIFY, PRINTLN },
                       _tokenFactorySourcePair );

    @Override
    public Token nextToken() {
        final Token token = m_dentation.nextToken();

        emit( token );

        return token;
    }
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
MODIFY      : 'modify' ;
NOT         : 'not' ;
OR          : 'or' ;
PRINTLN     : 'println' ;
RELATION    : 'relation' ;
RETRACT     : 'retract' ;
RULE        : 'rule' ;

TRUE        : 'true' ;
FALSE       : 'false' ;

// Integer ('int' and 'long') Literals

IntegerLiteral
    : Sign? DecimalNumeral
    | Sign? HexNumeral
    | Sign? OctalNumeral
    | Sign? BinaryNumeral
    ;

LongIntegerLiteral
    : IntegerLiteral LongIntegerSuffix
    ;

fragment
LongIntegerSuffix
    : [lL]
    ;

fragment
DecimalNumeral
    : '0'
    | NonZeroDigit (Digits? | Underscores Digits)
    ;

fragment
Digits
    : Digit (DigitsAndUnderscores? Digit)?
    ;

fragment
Digit
    : '0'
    | NonZeroDigit
    ;

fragment
NonZeroDigit
    : [1-9]
    ;

fragment
DigitsAndUnderscores
    : DigitOrUnderscore+
    ;

fragment
DigitOrUnderscore
    : Digit
    | '_'
    ;

fragment
Underscores
    : '_'+
    ;

fragment
HexNumeral
    : '0' [xX] HexDigits
    ;

fragment
HexDigits
    : HexDigit (HexDigitsAndUnderscores? HexDigit)?
    ;

fragment
HexDigit
    : [0-9a-fA-F]
    ;

fragment
HexDigitsAndUnderscores
    : HexDigitOrUnderscore+
    ;

fragment
HexDigitOrUnderscore
    : HexDigit
    | '_'
    ;

fragment
OctalNumeral
    : '0' Underscores? OctalDigits
    ;

fragment
OctalDigits
    : OctalDigit (OctalDigitsAndUnderscores? OctalDigit)?
    ;

fragment
OctalDigit
    : [0-7]
    ;

fragment
OctalDigitsAndUnderscores
    : OctalDigitOrUnderscore+
    ;

fragment
OctalDigitOrUnderscore
    : OctalDigit
    | '_'
    ;

fragment
BinaryNumeral
    : '0' [bB] BinaryDigits
    ;

fragment
BinaryDigits
    : BinaryDigit (BinaryDigitsAndUnderscores? BinaryDigit)?
    ;

fragment
BinaryDigit
    : [01]
    ;

fragment
BinaryDigitsAndUnderscores
    : BinaryDigitOrUnderscore+
    ;

fragment
BinaryDigitOrUnderscore
    : BinaryDigit
    | '_'
    ;

// Floating-Point ('float' and 'double') Literals

FloatingPointLiteral
    : Sign? DecimalFloatingPointLiteral
    | Sign? HexadecimalFloatingPointLiteral
    ;

DoubleFloatingPointLiteral
    : Sign? DecimalDoubleFloatingPointLiteral
    | Sign? HexadecimalDoubleFloatingPointLiteral
    ;

fragment
FloatTypeSuffix
    : [fF]
    ;

fragment
DoubleTypeSuffix
    : [dD]
    ;

fragment
DecimalFloatingPointLiteral
    : Digits '.' Digits? ExponentPart? FloatTypeSuffix
    | '.' Digits ExponentPart? FloatTypeSuffix
    | Digits ExponentPart FloatTypeSuffix
    | Digits FloatTypeSuffix
    ;

fragment
DecimalDoubleFloatingPointLiteral
    : Digits '.' Digits? ExponentPart? DoubleTypeSuffix?
    | '.' Digits ExponentPart? DoubleTypeSuffix?
    | Digits ExponentPart DoubleTypeSuffix?
    | Digits DoubleTypeSuffix
    ;

fragment
ExponentPart
    : ExponentIndicator SignedInteger
    ;

fragment
ExponentIndicator
    : [eE]
    ;

fragment
SignedInteger
    : Sign? Digits
    ;

fragment
Sign
    : [+-]
    ;

fragment
HexadecimalFloatingPointLiteral
    : HexSignificand BinaryExponent FloatTypeSuffix
    ;

fragment
HexadecimalDoubleFloatingPointLiteral
    : HexSignificand BinaryExponent DoubleTypeSuffix?
    ;

fragment
HexSignificand
    : HexNumeral '.'?
    | '0' [xX] HexDigits? '.' HexDigits
    ;

fragment
BinaryExponent
    : BinaryExponentIndicator SignedInteger
    ;

fragment
BinaryExponentIndicator
    : [pP]
    ;

// §3.10.4 Character Literals

CharacterLiteral
    : '\'' SingleCharacter '\''
    | '\'' EscapeSequence '\''
    ;

fragment
SingleCharacter
    : ~['\\]
    ;

// §3.10.5 String Literals

StringLiteral
    : '"' StringCharacters? '"'
    ;

fragment
StringCharacters
    : StringCharacter+
    ;

fragment
StringCharacter
    : ~["\\]
    | EscapeSequence
    ;

// §3.10.6 Escape Sequences for Character and String Literals

fragment
EscapeSequence
    : '\\' [btnfr"'\\]
    | OctalEscape
    ;

fragment
OctalEscape
    : '\\' OctalDigit
    | '\\' OctalDigit OctalDigit
    | '\\' ZeroToThree OctalDigit OctalDigit
    ;

fragment
ZeroToThree
    : [0-3]
    ;

// §3.10.7 The Null Literal

NullLiteral
    : 'null'
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
    : ':' Identifier
    ;

Variable
    : '?' Identifier
    ;

Identifier
    : Letter LetterOrDigit*
    ;

fragment
Letter
    : // these are the "letters" below 0xFF
      [a-zA-Z$_]
    | // covers all characters above 0xFF which are not a surrogate
      ~[\u0000-\u00FF\uD800-\uDBFF]
      { Character.isJavaIdentifierStart(_input.LA(-1)) }?
    | // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
      [\uD800-\uDBFF] [\uDC00-\uDFFF]
      { Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
    ;

fragment
LetterOrDigit
    : // these are the "letters or digits" below 0xFF
      [a-zA-Z0-9$_]
    | // covers all characters above 0xFF which are not a surrogate
      ~[\u0000-\u00FF\uD800-\uDBFF]
      { Character.isJavaIdentifierPart(_input.LA(-1)) }?
    | // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
      [\uD800-\uDBFF] [\uDC00-\uDFFF]
      { Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1))) }?
    ;

// Whitespace and comments

NL
    : EOL SPACES?
    | SPACES { _tokenStartCharIndex == 0 }?
    ;

WS : ( SPACES | LINE_COMMENT ) -> skip ;

fragment
EOL : ( '\r'? '\n' | '\r' | '\f' ) ;

fragment
SPACES : ' '+ ;

fragment
LINE_COMMENT : ';' ~[\r\n\f]* ;
