package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.stream.Stream;

public class Parser {

    private final Deque<Character> elementStack;

    public Parser() {
        elementStack = new ArrayDeque<>();
    }

    public void parseFile(final String filename) throws ParsingException {
        try {
            Path path = Paths.get(Objects.requireNonNull(Parser.class.getClassLoader().getResource(filename)).toURI());
            parse(path);
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
        }
    }

    public Deque<Character> getElementStack() {
        return elementStack;
    }

    private void parse(Path path) throws ParsingException {
        try (Stream<String> lineStream = Files.lines(path)) {
            for (String line : lineStream.toList()) {
                // get the line as string
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}


