package bean;

import support.comparators.CharPriority;

import java.util.Arrays;

public enum Operator {
    LEFT_BRACKET("(", 0),
    RIGHT_BRACKET(")", 0),
    POW("^", 1),
    PLUS("+", 2),
    MINUS("-", 2),
    MULTIPLY("*", 3),
    DIVINE("/", 3),
    PERCENT("%", 3),

    DEFAULT();

    private String symbol;
    private int priority;

    Operator() {
        priority = 4;
    }

    Operator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public static Operator find(char c) {
        return find(String.valueOf(c));
    }

    public static Operator find(String s) {
        return Arrays.stream(values()).filter(operator -> operator.symbol.equals(s)).findFirst().orElse(DEFAULT);
    }

    public static boolean typeOf(char c) {
        return typeOf(String.valueOf(c));
    }

    public static boolean typeOf(String s) {
        return find(s).symbol.equals(s);
    }

    public static int comparePriority(char c1, char c2) {
        return new CharPriority().compare(c1, c2);
    }

    public static boolean isBracket(char c) {
        return isBracket(String.valueOf(c));
    }

    public static boolean isBracket(String s) {
        return s.equals(LEFT_BRACKET.symbol) || s.equals(RIGHT_BRACKET.symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }
}
