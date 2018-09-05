package support;

import beans.elements.Operator;
import exceptions.CalculatorException;

public class BracketsManager {
    public boolean areBracketsAgreed(String s) {
        int check = 0;
        if (s.contains("()")) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') check++;
                if (s.charAt(i) == ')') check--;
            }
        }
        if (check != 0) throw new CalculatorException("Brackets are not agreed.");
        else return true;
    }

    public boolean isBracket(String s) {
        return s.equals(Operator.Content.LEFT_BRACKET.getSymbol()) || s.equals(Operator.Content.RIGHT_BRACKET.getSymbol());
    }
}
