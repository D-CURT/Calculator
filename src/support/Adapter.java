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
        String after_brack_num;
        int i_input_brack = 0, i_res_brack = 0;
        char[] s = input.toCharArray();
        ArrayDeque<String> operators = new ArrayDeque<>();

        for (int i = 0; i < s.length; i++) {
            result.append(current.append(ELEMENT.readElement(s, i, ELEMENT.getType(s[i]))));
            if (OPERATOR.found(current)) {
                if (current.toString().equals(POW)) {
                    result.append(LEFT_BRACKET);
                    i_input_brack = i - current.length();
                }
                if (!operators.isEmpty() && !operators.peek().equals(current.toString())
                        && operators.peek().equals(POW)) {
                    while (!operators.isEmpty() && operators.peek().equals(POW)) {
                        after_brack_num = ELEMENT.readElement(s, i_input_brack + 2, ELEMENT.getType(s[i_input_brack + 2]));
                        int res_lng = result.length() - 1, i_brack = result.lastIndexOf(LEFT_BRACKET);

                        if (i_brack == res_lng - after_brack_num.length() - current.length()) result.deleteCharAt(i_brack);
                        else result.insert(result.length() - 1, RIGHT_BRACKET);
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
