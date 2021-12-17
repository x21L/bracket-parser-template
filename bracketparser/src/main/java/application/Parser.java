package application;

import java.util.ArrayDeque;
import java.util.Deque;

public class Parser {

    private final Deque<Character> elementStack;

    public Parser() {
        elementStack = new ArrayDeque<>();
    }

    public void parseFile(final String filename) throws ParsingException {
    }

    public Deque<Character> getElementStack() {
        return elementStack;
    }
}
