package support;


import java.util.ArrayDeque;

import static support.constants.Constants.*;

public class Adapter {
    public static String adapt(String s) {
        return adaptPow(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    private static String adaptPow(String input) {
        StringBuilder result = new StringBuilder();
        StringBuilder current = new StringBuilder();
        char[] s = input.toCharArray();
        ArrayDeque<String> operators = new ArrayDeque<>();

        for (int i = 0; i < s.length; i++) {
            result.append(current.append(ELEMENT.readElement(s, i, ELEMENT.getType(s[i]))));
            if (OPERATOR.found(current)) {
                if (current.toString().equals(POW)) result.append(LEFT_BRACKET);
                if (!operators.isEmpty() && !operators.peek().equals(current.toString())
                        && operators.peek().equals(POW)) {
                    while (!operators.isEmpty() && operators.peek().equals(POW)) {
                        result.insert(result.length() - 1, RIGHT_BRACKET);
                        operators.pop();
                    }
                }
                operators.push(current.toString());
            }
            current.setLength(0);
        }
        while (!operators.isEmpty() && operators.peek().equals(POW)) {
            result.append(")");
            operators.pop();
        }
        return result.toString();
    }
}
