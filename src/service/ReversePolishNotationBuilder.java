package service;

import bean.Operator;

import java.util.ArrayDeque;

public class ReversePolishNotationBuilder {

    public String getRPN(String input) {
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
    }

    public boolean isOperator(Character c) {
        return "()+-*/^".indexOf(c) != -1;
    }


}
