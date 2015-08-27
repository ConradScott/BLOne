package uk.me.conradscott.blone.compiler;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.compiler.builder.ProgramBuilder;
import uk.me.conradscott.blone.compiler.printer.ProgramPrinter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.stream.Collectors;

public final class BLOne {
    @NotNull private static final Logger LOGGER = LogManager.getLogger( BLOne.class );

    @NotNull private static final String NAME = "test.bl1";

    private BLOne() {}

    public static void main( final String... args ) {
        final URL resource = BLOne.class.getClassLoader().getResource( NAME );

        if ( resource == null ) {
            LOGGER.error( "Cannot find resource \"" + NAME + "\"." );
            return;
        }

        final ErrorCollector errorCollector = new ErrorCollector(LOGGER);

        final ANTLRInputStream input;

        try {
            input = new ANTLRFileStream( resource.getFile() );
        } catch ( final FileNotFoundException e ) {
            LOGGER.error( "Cannot open resource \"" + NAME + "\".", e );
            return;
        } catch ( final IOException e ) {
            LOGGER.error( "Failed to read from resource \"" + NAME + "\".", e );
            return;
        }

        final BLOneLexer lexer = buildLexer( errorCollector, input );

        final CommonTokenStream tokens = new CommonTokenStream( lexer );

        // logTokenStream(tokens);

        final BLOneParser parser = buildParser( errorCollector, tokens );

        final ProgramBuilder visitor = new ProgramBuilder( errorCollector );

        final BLOneParser.ProgramContext ctx = parser.program();

        if ( errorCollector.getErrors() != 0 ) {
            final MessageFormat format = new MessageFormat( "{0} {0,choice,0#errors|1#error|1<errors}." );
            LOGGER.error( format.format( new Object[] { errorCollector.getErrors() } ) );
            return;
        }

        visitor.build( ctx );

        ProgramPrinter.print( System.out, visitor );
    }

    @NotNull private static BLOneLexer buildLexer( final ErrorCollector errorCollector, final ANTLRInputStream input ) {
        final BLOneLexer lexer = new BLOneLexer( input );
        lexer.removeErrorListeners();

        lexer.addErrorListener( new SyntaxErrorCollector( errorCollector ) );
        return lexer;
    }

    private static void logTokenStream( final CommonTokenStream tokens ) {
        tokens.fill();
        LOGGER.info( tokens.getTokens()
                           .stream()
                           .map( token -> token.getText() + '[' + token.getType() + ']' + String.format( "%n" ) )
                           .collect( Collectors.joining( " " ) ) );
    }

    @NotNull
    private static BLOneParser buildParser( final ErrorCollector errorCollector, final CommonTokenStream tokens ) {
        final BLOneParser parser = new BLOneParser( tokens );
        parser.removeErrorListeners();

        parser.addErrorListener( new SyntaxErrorCollector( errorCollector ) );

        // Test for ambiguity in the grammar.
        parser.addErrorListener( new DiagnosticErrorListener() );
        parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
        return parser;
    }
}
