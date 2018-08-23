package support;


import bean.Operator;

import java.util.ArrayDeque;

import static support.constants.Constants.*;

public class Adapter {
    public static void adapt(String s) {
        s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
    }

    /*private void adaptPow(String input) {
        StringBuilder sb = new StringBuilder();
        char[] s = input.toCharArray();
        ArrayDeque<Character> operatorStack = new ArrayDeque<>();

        for (int i = 0; i < s.length; i++) {
            if (Operator.find(s[i])) {
                sb.append(tokenList.get(i));
                if (tokenList.get(i).equals("^")) sb.append("(");
                if (operatorStack.size() > 1 && !operatorStack.peek().equals(tokenList.get(i))
                        && operatorStack.peek().equals("^")) {
                    while (!operatorStack.empty() && operatorStack.peek().equals("^")) {
                        sb.insert(sb.length() - 1, ")");
                        operatorStack.pop();
                    }
                    operatorStack.push(tokenList.get(i));
                } else {
                    operatorStack.push(tokenList.get(i));
                }
            } else {
                sb.append(tokenList.get(i));
                if (!operatorStack.empty() && i == tokenList.size() - 1 && operatorStack.peek().equals("^")) {
                    while (!operatorStack.empty() && operatorStack.peek().equals("^")) {
                        sb.append(")");
                        operatorStack.pop();
                    }
                }
            }
        }
        input = sb.toString();
    }*/
}
