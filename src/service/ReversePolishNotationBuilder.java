package service;

import bean.Operator;

import java.util.ArrayDeque;

public class ReversePolishNotationBuilder {

    public String getRPN(String s) {
        ArrayDeque<Character> operators = new ArrayDeque<>();
        String space = " ";
        StringBuilder result = new StringBuilder();


        for (int i = 0; i < s.length(); i++) {

            if (Character.isDigit(s.charAt(i))) {
                while (!isOperator(s.charAt(i))) {
                    result.append(s.charAt(i)).append(space);
                    i++;
                    if (i == s.length()) break;
                }
                i--;
            } else if (isOperator(s.charAt(i))) {
                Character operator;
                if (s.charAt(i) == '(') {
                    operators.push(s.charAt(i));
                } else if (s.charAt(i) == ')') {
                    operator = operators.pop();
                    while (operator != '(') {
                        result.append(operator).append(space);
                        operator = operators.pop();
                    }
                } else {
                    if (!operators.isEmpty()) {
                        while (Operator.comparePriority(s.charAt(i), operators.peek()) < 1)
                            result.append(operators.pop()).append(space);
                    }
                    operators.push(s.charAt(i));
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
