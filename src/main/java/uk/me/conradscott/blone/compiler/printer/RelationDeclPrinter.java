package uk.me.conradscott.blone.compiler.printer;

import uk.me.conradscott.blone.ast.type.RelationDecl;

import java.io.PrintStream;

final class RelationDeclPrinter {
    private RelationDeclPrinter() {}

    static void print( final PrintStream out, final Iterable< RelationDecl > relationDecls, final int depth ) {
        relationDecls.forEach( relationDecl -> print( out, relationDecl, depth ) );
    }

    static void print( final PrintStream out, final RelationDecl relationDecl, final int depth ) {
        Formatter.begin( out, relationDecl, depth );

        Formatter.format( out, "name", relationDecl.getName(), depth + 1 );
        relationDecl.getDocumentationString()
                    .ifPresent( s -> Formatter.format( out, "documentationString", s.getValue(), depth + 1 ) );
        AttributeDeclPrinter.print( out, relationDecl, depth + 1 );

        Formatter.end( out );
    }
}
