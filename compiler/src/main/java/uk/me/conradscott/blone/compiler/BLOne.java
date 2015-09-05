package uk.me.conradscott.blone.compiler;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.scope.Program;
import uk.me.conradscott.blone.compiler.builder.ProgramBuilder;
import uk.me.conradscott.blone.compiler.printer.ProgramPrinter;
import uk.me.conradscott.blone.compiler.typechecker.ProgramTypeChecker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class BLOne {
    private static final Logger LOGGER = LogManager.getLogger( BLOne.class );

    private enum StatusCode {
        SUCCESS( 0 ), ERROR( 1 );

        private final int m_status;

        StatusCode( final int status ) {
            m_status = status;
        }

        private int getStatus() {
            return m_status;
        }

        private static StatusCode reduce( final StatusCode lhs, final StatusCode rhs ) {
            return ( lhs.m_status > rhs.m_status ) ? lhs : rhs;
        }
    }

    private BLOne() {}

    public static void main( final String... args ) {
        final StatusCode status = Arrays.stream( args )
                                        .map( BLOne::compile )
                                        .reduce( StatusCode.SUCCESS, StatusCode::reduce );

        System.exit( status.getStatus() );
    }

    private static StatusCode compile( final String pathname ) {
        final ANTLRFileStream input;

        try {
            input = new ANTLRFileStream( pathname );
        } catch ( final FileNotFoundException e ) {
            LOGGER.error( "Cannot open file \"" + pathname + "\": " + e.getMessage() );
            return StatusCode.ERROR;
        } catch ( final IOException e ) {
            LOGGER.error( "Failed to read from file \"" + pathname + "\": " + e.getMessage() );
            return StatusCode.ERROR;
        }

        final ErrorCollector errorCollector = new ErrorCollector( LOGGER );

        final BLOneLexer lexer = buildLexer( errorCollector, input );

        final CommonTokenStream tokens = new CommonTokenStream( lexer );

        // logTokenStream(tokens);

        final BLOneParser parser = buildParser( errorCollector, tokens );

        final BLOneParser.ProgramContext ctx = parser.program();

        if ( errorCollector.getErrors() != 0 ) {
            errorCollector.reportErrors( LOGGER );
            return StatusCode.ERROR;
        }

        final Program program = new Program();

        ProgramBuilder.build( errorCollector, program, ctx );

        if ( errorCollector.getErrors() != 0 ) {
            errorCollector.reportErrors( LOGGER );
            return StatusCode.ERROR;
        }

        ProgramTypeChecker.check( errorCollector, program );

        if ( errorCollector.getErrors() != 0 ) {
            errorCollector.reportErrors( LOGGER );
            return StatusCode.ERROR;
        }

        ProgramPrinter.print( System.out, program );

        return StatusCode.SUCCESS;
    }

    private static BLOneLexer buildLexer( final ErrorCollector errorCollector, final ANTLRInputStream input ) {
        final BLOneLexer lexer = new BLOneLexer( input );
        lexer.removeErrorListeners();

        lexer.addErrorListener( new ANTLRErrorCollectorAdapter( errorCollector ) );
        return lexer;
    }

    private static void logTokenStream( final CommonTokenStream tokens ) {
        tokens.fill();
        LOGGER.info( tokens.getTokens()
                           .stream()
                           .map( token -> token.getText() + '[' + token.getType() + ']' + String.format( "%n" ) )
                           .collect( Collectors.joining( " " ) ) );
    }

    private static BLOneParser buildParser( final ErrorCollector errorCollector, final CommonTokenStream tokens ) {
        final BLOneParser parser = new BLOneParser( tokens );
        parser.removeErrorListeners();

        parser.addErrorListener( new ANTLRErrorCollectorAdapter( errorCollector ) );

        // Test for ambiguity in the grammar.
        parser.addErrorListener( new DiagnosticErrorListener() );
        parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
        return parser;
    }
}
