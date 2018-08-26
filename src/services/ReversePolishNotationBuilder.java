package services;

import beans.Operator;
import support.Adapter;

import java.util.ArrayDeque;

import static support.constants.Constants.*;

public class ReversePolishNotationBuilder {

    public String getRPN(String input) {
        ArrayDeque<String> operators = new ArrayDeque<>();
        char[] s = Adapter.adapt(input).toCharArray();
        StringBuilder result = new StringBuilder();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            current.append(ELEMENT.readElement(s, i, ELEMENT.getType(s[i])));
            if (OPERAND.found(current)) {
                result.append(current).append(SPACE);
            } else if (OPERATOR.found(current)) {
                replaceOperator(current, operators, result);
            }
            current.setLength(0);
        }
        while (!operators.isEmpty())
            result.append(operators.pop()).append(operators.isEmpty() ? EMPTY : SPACE);
        return result.toString();
    }

    private void replaceOperator(StringBuilder current, ArrayDeque<String> operators,
                                 StringBuilder result) {
        if (OPERATOR.isBracket(current)) {
            replaceBrackets(current, operators, result);
        } else {
            if (!operators.isEmpty()) {
                while (Operator.comparePriority(current.toString(), operators.peek()) < 1) {
                    result.append(operators.pop()).append(SPACE);
                }
            }
            operators.push(current.toString());
        }
    }

    private void replaceBrackets(StringBuilder current, ArrayDeque<String> operators,
                                 StringBuilder result) {
        StringBuilder operator = new StringBuilder();
        if (current.toString().equals(LEFT_BRACKET)) {
            operators.push(current.toString());
        } else if (current.toString().equals(RIGHT_BRACKET)) {
            operator.append(operators.pop());
            while (!operator.toString().equals(LEFT_BRACKET)) {
                result.append(operator).append(SPACE);
                operator.setLength(0);
                operator.append(operators.pop());
            }
        }
    }
}
