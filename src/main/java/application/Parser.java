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

    private final Deque<Character> bracketStack;
    private final Deque<Character> elementStack;

    public Parser() {
        bracketStack = new ArrayDeque<>();
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
                parseLine(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        if (!bracketStack.isEmpty()) {
            throw new ParsingException("Error parsing the file. } expected");
        }
    }

    private void parseLine(String line) throws ParsingException {
        for (char character : line.toCharArray()) {
            // System.out.println("Current character: " + character);
            elementStack.add(character);
            checkElement(character);
        }
    }

    private void checkElement(final char character) throws ParsingException {
        switch (character) {
            case '{' -> bracketStack.add(character);
            case '}' -> {
                if (bracketStack.isEmpty()) {
                    throw new ParsingException("Error parsing the file. { expected");
                }
                bracketStack.pop();
            }
            default -> {
                // some other parsing
            }
        }
    }
}


