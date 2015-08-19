package uk.me.conradscott.blone.ast;

import org.jetbrains.annotations.NotNull;

public enum PrimitiveType implements TypeIfc {
    BOOLEAN("boolean", Boolean.class),
    BYTE("byte", Byte.class),
    SHORT("short", Short.class),
    INT("int", Integer.class),
    LONG("long", Long.class),
    FLOAT("float", Float.class),
    DOUBLE("double", Double.class),
    CHAR("char", Character.class),
    STRING("string", String.class);

    private final String m_name;
    private final Class<?> m_javaClass;

    PrimitiveType(@NotNull final String name, @NotNull final Class<?> javaClass) {
        m_name = name;
        m_javaClass = javaClass;
    }

    @NotNull @Override public String getName() {
        return m_name;
    }

    @NotNull Class<?> getJavaClass() {
        return m_javaClass;
    }
}
