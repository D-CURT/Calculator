package beans.elements;

import abstractions.AbstractElement;
import exceptions.CalculatorException;
import interfaces.functional_interfaces.FI_Operator_count;
import beans.elements.sub_elements.BracketsManager;
import support.comparators.CharPriority;

import java.util.Arrays;

public class Operator extends AbstractElement {
    public final BracketsManager bracket = new BracketsManager();

    public enum Content {
        LEFT_BRACKET("(", 1),
        RIGHT_BRACKET(")", 1),
        PLUS("+", 2, (t1, t2) -> t1 + t2),
        MINUS("-", 2, (t1, t2) -> t1 - t2),
        MULTIPLY("*", 3, (t1, t2) -> t1 * t2),
        DIVIDE("/", 3, Content::divide),
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

        public Double count(Double n1, Double n2) {
            return function.count(n1, n2);
        }

        public static Double divide(Double n1, Double n2) {
            if (n2 == 0) throw new CalculatorException("Division by zero!");
            else return n1 / n2;
        }

        public String getSymbol() {
            return symbol;
        }

        public int getPriority() {
            return priority;
        }
    }

    public static int comparePriority(String s1, String s2) {
        return new CharPriority().compare(s1, s2);
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
}