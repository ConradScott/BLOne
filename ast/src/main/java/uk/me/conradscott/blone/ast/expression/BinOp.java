package uk.me.conradscott.blone.ast.expression;

@SuppressWarnings( "HardcodedFileSeparator" ) public enum BinOp {
    GT( ">" ),
    LT( "<" ),
    EQUAL( "==" ),
    LE( "<=" ),
    GE( ">=" ),
    NOTEQUAL( "!=" ),
    AND( "&&" ),
    OR( "||" ),
    ADD( "+" ),
    SUB( "-" ),
    MUL( "*" ),
    DIV( "/" ),
    BITAND( "&" ),
    BITOR( "|" ),
    XOR( "^" ),
    MOD( "%" );

    private final String m_name;

    BinOp( final String name ) {
        m_name = name;
    }

    String getName() {
        return m_name;
    }
}
