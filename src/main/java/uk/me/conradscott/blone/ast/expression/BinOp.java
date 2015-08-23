package uk.me.conradscott.blone.ast.expression;

import org.jetbrains.annotations.NotNull;

public enum BinOp {
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

    @NotNull private final String m_name;

    BinOp( @NotNull final String name ) {
        m_name = name;
    }

    @NotNull String getName() {
        return m_name;
    }
}
