package beans;

import beans.abstractions.AbstractElement;
import support.comparators.CharPriority;

import java.util.Arrays;

public class Operator extends AbstractElement {
    public enum Content {
        LEFT_BRACKET("(", 1),
        RIGHT_BRACKET(")", 1),
        PLUS("+", 2),
        MINUS("-", 2),
        MULTIPLY("*", 3),
        DIVINE("/", 3),
        PERCENT("%", 3),
        POW("^", 4),

        DEFAULT();

        private String symbol;
        private int priority;

        Content() {
            symbol = "";
            priority = 0;
        }

        Content(String symbol, int priority) {
            this.symbol = symbol;
            this.priority = priority;
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