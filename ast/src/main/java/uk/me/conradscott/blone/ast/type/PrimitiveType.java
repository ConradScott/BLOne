package uk.me.conradscott.blone.ast.type;

public enum PrimitiveType implements TypeIfc {
    BOOLEAN( "boolean", Boolean.class ),
    BYTE( "byte", Byte.class ),
    SHORT( "short", Short.class ),
    INT( "int", Integer.class ),
    LONG( "long", Long.class ),
    FLOAT( "float", Float.class ),
    DOUBLE( "double", Double.class ),
    CHAR( "char", Character.class ),
    STRING( "string", String.class );

    private final String m_name;
    private final Class< ? > m_javaClass;

    PrimitiveType( final String name, final Class< ? > javaClass ) {
        m_name = name;
        m_javaClass = javaClass;
    }

    @Override public String getName() {
        return m_name;
    }

    public Class< ? > getJavaClass() {
        return m_javaClass;
    }
}
