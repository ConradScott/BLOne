package uk.me.conradscott.blone.ast.type;

public interface TypeIfc {
    String getName();

    < T, R > R accept( TypeVisitorIfc< T, R > visitor, T t );
}
