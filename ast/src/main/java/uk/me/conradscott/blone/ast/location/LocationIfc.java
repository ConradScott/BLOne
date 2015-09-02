package uk.me.conradscott.blone.ast.location;

public interface LocationIfc {
    String getSourceFile();

    int getLineno();

    int getColumn();
}
