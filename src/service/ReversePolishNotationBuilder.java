package service;

import bean.Operator;

import java.util.ArrayDeque;

public class ReversePolishNotationBuilder {
    private static final String SPACE = " ";

    public String getRPN(String input) {
        ArrayDeque<Character> operators = new ArrayDeque<>();
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
            result.append(operators.pop()).append(operators.isEmpty() ? "" : SPACE);
        return result.toString();
    }

    private void replaceOperand(char[] inputChars, StringBuilder resultString, int iterator) {
        while (!isOperator(inputChars[iterator])) {
            resultString.append(inputChars[iterator]).append(SPACE);
            iterator++;
            if (iterator == inputChars.length) break;
        }
        iterator--;
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
        Character operator;
        if (inputChars[iterator] == Operator.LEFT_BRACKET.getSymbol()) {
            operators.push(inputChars[iterator]);
        } else if (inputChars[iterator] == Operator.RIGHT_BRACKET.getSymbol()) {
            operator = operators.pop();
            while (operator != Operator.LEFT_BRACKET.getSymbol()) {
                resultString.append(operator).append(SPACE);
                operator = operators.pop();
            }
        }
    }

    public boolean isOperator(Character c) {
        return "()+-*/^".indexOf(c) != -1;
    }


}
