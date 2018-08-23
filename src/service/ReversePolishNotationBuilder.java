package service;

import bean.Operator;
import support.Adapter;

import java.util.ArrayDeque;
import java.util.Arrays;

import static support.constants.Constants.*;

public class ReversePolishNotationBuilder {

    public String getRPN(String input) {
        ArrayDeque<Character> operators = new ArrayDeque<>();
        Adapter.adapt(input);
        char[] s = input.toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length; i++) {

            if (Character.isDigit(s[i])) {
                replaceOperand(s, result, i);
            } else if (isOperator(s[i])) {
                replaceOperator(s, operators, result, i);
            }
        }
        while (!operators.isEmpty())
            result.append(operators.pop()).append(operators.isEmpty() ? EMPTY : SPACE);
        return result.toString();
    }

    private void replaceOperand(char[] inputChars, StringBuilder resultString, int iterator) {
        while (!isOperator(inputChars[iterator])) {
            resultString.append(inputChars[iterator]).append(SPACE);
            iterator++;
            if (iterator == inputChars.length) break;
        }
    }

    private void replaceOperator(char[] inputChars, ArrayDeque<Character> operators,
                                 StringBuilder resultString, int iterator) {
        if (Operator.isBracket(inputChars[iterator])) {
            replaceBrackets(inputChars, operators, resultString, iterator);
        } else {
            if (!operators.isEmpty()) {
                while (Operator.comparePriority(inputChars[iterator], operators.peek()) < 1)
                    resultString.append(operators.pop()).append(SPACE);
            }
            operators.push(inputChars[iterator]);
        }
    }

    private void replaceBrackets(char[] inputChars, ArrayDeque<Character> operators,
                                 StringBuilder resultString, int iterator) {
        char operator;
        if (inputChars[iterator] == LEFT_BRACKET) {
            operators.push(inputChars[iterator]);
        } else if (inputChars[iterator] == RIGHT_BRACKET) {
            operator = operators.pop();
            while (operator != LEFT_BRACKET) {
                resultString.append(operator).append(SPACE);
                operator = operators.pop();
            }
        }
    }

    public boolean isOperator(char c) {
        return "()+-*/%^".indexOf(c) != -1;
    }


}
