package services;

import beans.Operator;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.*;

public class ReversePolishNotationBuilder {

    public List<String> getRPN_asList(List<String> s) {
        ArrayDeque<String> operators = new ArrayDeque<>();
        List<String> result = new LinkedList<>();
        String current;

        for (int i = 0; i < s.size(); i++) {
            current = s.get(i);
            if (OPERAND.found(current)) {
                result.add(current); result.add(SPACE);
            } else if (OPERATOR.found(current)) {
                replaceOperator(current, operators, result);
            }
        }
        while (!operators.isEmpty()) {
            result.add(operators.pop()); result.add(operators.isEmpty() ? EMPTY : SPACE);
        }
        return result;
    }

    private void replaceOperator(String current, ArrayDeque<String> operators,
                                 List<String> result) {
        if (OPERATOR.isBracket(current)) {
            replaceBrackets(current, operators, result);
        } else {
            if (!operators.isEmpty()) {
                while (Operator.comparePriority(current, operators.peek()) < 1) {
                    result.add(operators.pop()); result.add(SPACE);
                }
            }
            operators.push(current);
        }
    }

    private void replaceBrackets(String current, ArrayDeque<String> operators,
                                 List<String> result) {
        String operator;
        if (current.equals(LEFT_BRACKET)) {
            operators.push(current);
        } else if (current.equals(RIGHT_BRACKET)) {
            operator = operators.pop();
            while (!operator.equals(LEFT_BRACKET)) {
                result.add(operator); result.add(SPACE);
                operator = operators.pop();
            }
        }
    }
}
