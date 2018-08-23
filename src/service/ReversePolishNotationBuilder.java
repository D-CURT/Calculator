package service;

import bean.Operator;

import java.util.ArrayDeque;

public class ReversePolishNotationBuilder {
    private static final String SPACE = " ";

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
        Character operator;
        if (inputChars[iterator] == Operator.LEFT_BRACKET.getSymbol()) {
            operators.push(inputChars[iterator]);
        } else if (inputChars[iterator] == Operator.RIGHT_BRACKET.getSymbol()) {
            operator = operators.pop();
            while (operator != Operator.LEFT_BRACKET.getSymbol()) {
                resultString.append(operator).append(SPACE);
                operator = operators.pop();
            }
        } else {
            if (!operators.isEmpty()) {
                while (Operator.comparePriority(inputChars[iterator], operators.peek()) < 1)
                    resultString.append(operators.pop()).append(SPACE);
            }
            operators.push(inputChars[iterator]);
        }
    }

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

    /*public String getRPN(String input) {
        ArrayDeque<Character> operators = new ArrayDeque<>();
        char[] s = input.toCharArray();
        String space = " ";
        StringBuilder result = new StringBuilder();


        for (int i = 0; i < s.length; i++) {

            if (Character.isDigit(s[i])) {
                while (!isOperator(s[i])) {
                    result.append(s[i]).append(space);
                    i++;
                    if (i == s.length) break;
                }
                i--;
            } else if (isOperator(s[i])) {
                Character operator;
                if (s[i] == '(') {
                    operators.push(s[i]);
                } else if (s[i] == ')') {
                    operator = operators.pop();
                    while (operator != '(') {
                        result.append(operator).append(space);
                        operator = operators.pop();
                    }
                } else {
                    if (!operators.isEmpty()) {
                        while (Operator.comparePriority(s[i], operators.peek()) < 1)
                            result.append(operators.pop()).append(space);
                    }
                    operators.push(s[i]);
                }
            }

        }
        while (!operators.isEmpty())
            result.append(operators.pop()).append(operators.isEmpty() ? "" : space);
        return result.toString();
    }*/

    public boolean isOperator(Character c) {
        return "()+-*/^".indexOf(c) != -1;
    }


}
