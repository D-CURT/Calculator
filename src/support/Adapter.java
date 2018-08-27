package support;


import beans.interfaces.FIAdapter_onPosition;

import java.util.ArrayDeque;

import static support.constants.Constants.*;

public class Adapter {
    public static final FIAdapter_onPosition NUMBER = Character::isDigit;
    public static final FIAdapter_onPosition LETTER = Character::isAlphabetic;
    public static String adapt(String s) {
        return adaptPow(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    /*private static String adaptPow(String input) {
        if (input.contains(POW)) {
            StringBuilder result = new StringBuilder();
            StringBuilder current = new StringBuilder();
            String num_after_brack;
            int i_input_brack = 0, iterator = 0;
            char c = 0;
            char[] s = input.toCharArray();
            ArrayDeque<String> operators = new ArrayDeque<>();

            for (int i = 0; i < s.length; i++) {

                result.append(current.append(ELEMENT.readElement(s, i, ELEMENT.getType(s[i]))));
                iterator = i + current.length();
                if (s[i] != s.length) c = (s[i + 1]);
                if (OPERATOR.found(current)) {
                    if (current.toString().equals(POW) && c != '(') {
                        result.append(LEFT_BRACKET);
                        i_input_brack = i - current.length();
                    }
                    if (!operators.isEmpty() && !operators.peek().equals(current.toString())
                            && operators.peek().equals(POW)) {
                        while (!operators.isEmpty() && operators.peek().equals(POW)) {

                            num_after_brack = ELEMENT.readElement(s, i_input_brack + 2, ELEMENT.getType(s[i_input_brack + 2]));
                            int res_lng = result.length() - 1, i_brack = result.lastIndexOf(LEFT_BRACKET);
                            c = s[i - 1];

                            if (i_brack == res_lng - num_after_brack.length() - current.length()) {
                                result.deleteCharAt(i_brack);
                            } else if (Character.isDigit(c))result.insert(result.length() - 1, RIGHT_BRACKET);
                            operators.pop();
                        }
                    }
                    operators.push(current.toString());
                }
                current.setLength(0);
            }
            while (!operators.isEmpty() && operators.peek().equals(POW)) {
                result.append(RIGHT_BRACKET);
                operators.pop();
            }
            return result.toString();
        }
        return input;
    }*/

    private static String adaptPow(String input) {
        if (input.contains(POW)) {
            char[] a = input.toCharArray();
            ArrayDeque<Object> operators = new ArrayDeque<>();
            StringBuilder current = new StringBuilder();
            StringBuilder tmp = new StringBuilder();
            StringBuilder result = new StringBuilder();
            boolean lb = false;
            int lb_i = 0;

            for (int i = 0; i < a.length; i++) {
                current.append(ELEMENT.readElement(a, i, ELEMENT.getType(a[i])));
                if (current.toString().equals(POW)) {
                    if (a[i] != a.length - 1) {
                        if (NUMBER.in(a[i + 1]) || LETTER.in(a[i + 1])) {
                            tmp.append(current).append(LEFT_BRACKET);
                            lb_i = tmp.lastIndexOf(LEFT_BRACKET);
                            operators.push(current);
                            current.setLength(0);
                        }
                        if (!lb) lb = true;
                    }
                } else if (lb) {
                    if (OPERATOR.found(current)) {
                        if (!current.toString().equals(POW)) {
                            if (i != 0) {
                                if (NUMBER.in(tmp.charAt(tmp.length() - 1))) {
                                    if (lb_i == tmp.length() - 2) {
                                        result.append(tmp.deleteCharAt(lb_i)).append(RIGHT_BRACKET).append(current);
                                        tmp.setLength(0);
                                        current.setLength(0);
                                        lb = false;
                                    }
                                }
                            }
                        }
                        result.append(tmp.deleteCharAt(1)).append(current);
                        tmp.setLength(0);
                        current.setLength(0);
                        lb = false;
                    } else {
                        if (i == a.length - 1) {
                            result.append(tmp.deleteCharAt(tmp.lastIndexOf(LEFT_BRACKET)))
                                    .append(current).append(RIGHT_BRACKET);
                            return result.toString();
                        } else  {
                            tmp.append(current);
                            current.setLength(0);
                        }
                    }
                } else {
                    result.append(current);
                    current.setLength(0);
                }
            }
        }
        return input;
    }
}
