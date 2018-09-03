package beans.elements;

import beans.abstractions.AbstractElement;
import interfaces.FI_Operator_count;
import support.comparators.CharPriority;

import java.util.Arrays;

public class Operator extends AbstractElement {
    public enum Content {
        LEFT_BRACKET("(", 1),
        RIGHT_BRACKET(")", 1),
        PLUS("+", 2, (t1, t2) -> t1 + t2),
        MINUS("-", 2, (t1, t2) -> t1 - t2),
        MULTIPLY("*", 3, (t1, t2) -> t1 * t2),
        DIVINE("/", 3, (t1, t2) -> t1 / t2),
        PERCENT("%", 3, (t1, t2) -> (t1 * t2) / 100),
        POW("^", 4, Math::pow),

        DEFAULT();

        private String symbol;
        private int priority;
        private FI_Operator_count<Double> function;

        Content() {
            symbol = "";
            priority = 0;
        }

        Content(String symbol, int priority) {
            this.symbol = symbol;
            this.priority = priority;
        }

        Content(String symbol, int priority, FI_Operator_count<Double> function) {
            this.symbol = symbol;
            this.priority = priority;
            this.function = function;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getPriority() {
            return priority;
        }
    }

    @Override
    public Content getElement(String s) {
        return Arrays.stream(Content.values())
                     .filter(Content -> Content.symbol != null && Content.symbol.equals(s))
                     .findFirst()
                     .orElse(Content.DEFAULT);
    }

    @Override
    public boolean found(String s) {
        return getElement(s).symbol.equals(s);
    }

    public static int comparePriority(String s1, String s2) {
        return new CharPriority().compare(s1, s2);
    }

    public boolean isBracket(String s) {
        return s.equals(Content.LEFT_BRACKET.symbol) || s.equals(Content.RIGHT_BRACKET.symbol);
    }
}