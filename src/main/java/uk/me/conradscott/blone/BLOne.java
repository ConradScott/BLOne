package uk.me.conradscott.blone;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.me.conradscott.blone.antlr4.BLOneLexer;
import uk.me.conradscott.blone.antlr4.BLOneParser;
import uk.me.conradscott.blone.ast.builder.antlr4.LoggingVisitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

public final class BLOne {
    private static final Logger LOGGER = LogManager.getLogger(BLOne.class);

    private static final String NAME = "test.bl1";

    private BLOne() {}

    public static void main(final String... args) {
        final URL resource = BLOne.class.getClassLoader().getResource(NAME);

        if (resource == null) {
            LOGGER.error("Cannot find resource \"" + NAME + "\".");
            return;
        }

        final File file = new File(resource.getFile());

        try (final FileInputStream stream = new FileInputStream(file)) {

            final BLOneLexer lexer = new BLOneLexer(new ANTLRInputStream(stream));

            final CommonTokenStream tokens = new CommonTokenStream(lexer);

            //            logTokenStream(tokens);

            final BLOneParser parser = new BLOneParser(tokens);

            final BLOneParser.ProgramContext context = parser.program();

            final ParseTreeVisitor<Void> visitor = new LoggingVisitor();
            visitor.visit(context);
        } catch (final FileNotFoundException e) {
            LOGGER.error("Cannot open resource \"" + NAME + "\".", e);
        } catch (final IOException e) {
            LOGGER.error("Failed to read from resource \"" + NAME + "\".", e);
        }
    }

    private static void logTokenStream(final CommonTokenStream tokens) {
        tokens.fill();
        LOGGER.info(tokens.getTokens()
                          .stream()
                          .map(token -> token.getText() + '[' + token.getType() + ']' + String.format("%n"))
                          .collect(Collectors.joining(" ")));
    }
}
