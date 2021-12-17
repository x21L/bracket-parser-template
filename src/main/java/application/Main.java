package application;

public class Main {
    public static void main(String[] args) {
        try {
            new Parser().parseFile("sample1.txt");
            System.out.println("Nice the parser worked correctly!!");
        } catch (ParsingException e) {
            System.err.println(e.getMessage());
        }
    }
}
