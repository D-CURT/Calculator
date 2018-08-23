import java.util.Arrays;
import java.util.Optional;

public enum Operator {
    LEFT_BRACKET('(', 1),
    RIGHT_BRACKET(')', 1),
    PLUS('+', 2),
    MINUS('-', 2),
    MULTIPLY('*', 3),
    DIVINE('/', 3),
    DEFAULT();

    private char symbol;
    private int priority;

    Operator() {
        symbol = '?';
        priority = 4;
    }

    Operator(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public static Operator find(char c) {
        return Arrays.stream(values()).filter(operator -> operator.symbol == c).findFirst().orElse(Operator.DEFAULT);
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }
}
