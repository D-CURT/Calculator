package bean;

import support.comparators.CharPriority;

import java.util.Arrays;

public enum Operator {
    DEFAULT(),
    DIVINE("/", 3),
    LEFT_BRACKET("(", 0),
    MINUS("-", 2),
    MULTIPLY("*", 3),
    PERCENT("%", 3),
    PLUS("+", 2),
    POW("^", 1),

    RIGHT_BRACKET(")", 0);

    private String symbol;
    private int priority;

    Operator() {
        symbol = "";
        priority = 4;
    }

    Operator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public static Operator find(StringBuilder sb) {
        return find(sb.toString());
    }

    public static Operator find(String s) {
        return Arrays.stream(values()).filter(operator -> operator.symbol != null && operator.symbol.equals(s)).
        findFirst().orElse(DEFAULT);
    }

    public static boolean isOperator(StringBuilder sb) {
        return isOperator(sb.toString());
    }

    public static boolean isOperator(String s) {
        return find(s).symbol.equals(s);
    }

    public static int comparePriority(StringBuilder sb1, StringBuilder sb2) {
        return comparePriority(sb1.toString(), sb2.toString());
    }

    public static int comparePriority(String s1, String s2) {
        return new CharPriority().compare(s1, s2);
    }

    public static boolean isBracket(StringBuilder sb) {
        return isBracket(sb.toString());
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
