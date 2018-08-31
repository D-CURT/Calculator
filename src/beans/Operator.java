package beans;

import beans.abstractions.AbstractElement;
import interfaces.FI_OperatorFunction;
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
        private FI_OperatorFunction<Double> function;

        Content() {
            symbol = "";
            priority = 0;
        }

        Content(String symbol, int priority) {
            this.symbol = symbol;
            this.priority = priority;
        }

        Content(String symbol, int priority, FI_OperatorFunction<Double> function) {
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

    public boolean found(StringBuilder sb) {
        return found(sb.toString());
    }

    @Override
    public boolean found(String s) {
        return getElement(s).symbol.equals(s);
    }

    public static int comparePriority(String s1, String s2) {
        return new CharPriority().compare(s1, s2);
    }

    public boolean isBracket(StringBuilder sb) {
        return isBracket(sb.toString());
    }

    public boolean isBracket(String s) {
        return s.equals(Content.LEFT_BRACKET.symbol) || s.equals(Content.RIGHT_BRACKET.symbol);
    }

   /* public boolean isUnaryMinus(String s, char[] chars, int i) {
        if (s.equals(MINUS)) {

            if (tokenList.size() != 1 && i == 0 && isOperand(tokenList.get(i + 1))) {
                if (tokenList.get(i).equals("-")) tokenList.set(i, "!");
                flag = true;
            } else if (i != tokenList.size() - 1 && i != 0 && tokenList.get(i - 1).equals("(") && isOperand(tokenList.get(i + 1))) {
                if (tokenList.get(i).equals("-")) tokenList.set(i, "!");
                flag = true;
            } else if (i != tokenList.size() - 1 && i != 0 && isOperator(tokenList.get(i - 1)) && isOperand(tokenList.get(i + 1))) {
                if (tokenList.get(i).equals("-")) tokenList.set(i, "!");
                flag = true;
            } else if (i != tokenList.size() - 1 && i != 0 && !tokenList.get(i).equals("-") && tokenList.get(i + 1).equals("(")) flag = true;
            if (!flag && !tokenList.get(i).equals("-")) tokenList.set(i, "?");

        }
    }*/
}