package support;


import beans.interfaces.FI_Adapter_onPosition;

import static support.constants.Constants.*;

public class Adapter {
    public static final FI_Adapter_onPosition NUMBER = Character::isDigit;
    public static final FI_Adapter_onPosition LETTER = Character::isAlphabetic;
    public static String adapt(String s) {
        return adaptPow(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    private static String adaptPow(String input) {
        if (input.contains(POW)) {
            char[] a = input.toCharArray();
            StringBuilder current = new StringBuilder();
            StringBuilder tmp = new StringBuilder();
            StringBuilder result = new StringBuilder();
            boolean lb = false, rb = false;
            int lb_i = 0;

            for (int i = 0; i < a.length; i++) {
                current.append(ELEMENT.readElement(a, i, ELEMENT.getType(a[i])));
                if (current.toString().equals(POW)) {
                    if (a[i] != a.length - 1) {
                        if (NUMBER.in(a[i + 1]) || LETTER.in(a[i + 1])) {
                            tmp.append(current).append(LEFT_BRACKET);
                            lb_i = tmp.lastIndexOf(LEFT_BRACKET);
                            current.setLength(0);
                        }
                        if (!lb) lb = true;
                    }
                } else if (lb) {
                    if (OPERATOR.found(current)) {
                        if (!current.toString().equals(POW)) {
                            if (i != a.length - 1) {
                                if (NUMBER.in(a[i + 1]) || LETTER.in(a[i + 1])) {
                                    tmp.append(RIGHT_BRACKET);
                                    rb = true;
                                }
                            }
                        }
                        if (rb) tmp.deleteCharAt(lb_i);
                        result.append(tmp).append(current);
                        tmp.setLength(0);
                        current.setLength(0);
                        lb = false; rb = false;
                    } else {
                        if (i == a.length - 1) {
                            result.append(tmp.deleteCharAt(lb_i))
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
