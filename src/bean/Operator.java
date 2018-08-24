package bean;

import support.comparator.CharPriority;

import java.util.Arrays;

public enum Operator {
    LEFT_BRACKET('(', 0),
    RIGHT_BRACKET(')', 0),
    POW('^', 1),
    PLUS('+', 2),
    MINUS('-', 2),
    MULTIPLY('*', 3),
    DIVINE('/', 3),
    PERCENT('%', 3),

    DEFAULT();

    private char symbol;
    private int priority;

    Operator() {
        priority = 4;
    }

    Operator(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public static Operator find(String s) {
        return find(s.charAt(0));
    }

    public static Operator find(char c) {
        return Arrays.stream(values()).filter(operator -> operator.symbol == c).findFirst().orElse(DEFAULT);
    }

    public static boolean typeOf(String s) {
        return typeOf(s.charAt(0));
    }

    public static boolean typeOf(char c) {
        return find(c).symbol == c;
    }

    public static int comparePriority(char c1, char c2) {
        return new CharPriority().compare(c1, c2);
    }

    public static boolean isBracket(char c) {
        return c == LEFT_BRACKET.symbol || c == RIGHT_BRACKET.symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }
}
