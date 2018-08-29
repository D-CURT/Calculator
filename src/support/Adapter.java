package support;

import static support.constants.Constants.*;

public class Adapter {
    public static String adapt(String s) {
        return adaptPow(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    private static String adaptPow(String s) {

        StringBuilder result = new StringBuilder();
        if (s.contains(POW)) {
            String current;
            char[] a = s.toCharArray();
            final int END = a.length - 1;
            int lb_c = 0;
            boolean lb = false;


            for (int i = 0; i < a.length; i++) {
                current = ELEMENT.readElement(a, i, ELEMENT.getType(a[i]));

                if (OPERATOR.found(current)) {
                    if (i != END) {
                        if (current.equals(POW)) {
                            if (NUMBER.in(a[i + 1]) || LETTER.in(a[i + 1])) {
                                result.append(current).append(LEFT_BRACKET);
                                lb_c++;
                                lb = true;
                            }
                        } else if (lb) {
                            while (lb_c > 0) {
                                result.append(RIGHT_BRACKET);
                                lb_c--;
                            }
                            lb = false;
                            result.append(current);
                        }
                    } else {
                        result.append(current);
                        if (lb) {
                            while (lb_c > 0) {
                                result.append(RIGHT_BRACKET);
                                lb_c--;
                            }
                        }
                    }
                } else {
                    result.append(current);
                    if (lb_c > 1) {
                        while (lb_c > 0) {
                            result.append(RIGHT_BRACKET);
                            lb_c--;
                        }
                    }
                }
            }
        }
        return result.toString();
    }
    /*private static String adaptPow(String input) {
        StringBuilder result = new StringBuilder();
        if (input.contains(POW)) {
            char[] a = input.toCharArray();
            StringBuilder current = new StringBuilder();
            StringBuilder tmp = new StringBuilder();

            boolean lb = false, rb = false, lb_r = false, rb_r = true;
            int lb_i = 0, lb_c = 0;

            for (int i = 0; i < a.length; i++) {
                current.append(ELEMENT.readElement(a, i, ELEMENT.getType(a[i])));
                if (current.toString().equals(POW)) {
                    if (a[i] != a.length - 1) {
                        if (NUMBER.in(a[i + 1]) || LETTER.in(a[i + 1])) {
                            tmp.append(current).append(LEFT_BRACKET);
                            lb_i = tmp.lastIndexOf(LEFT_BRACKET);
                            current.setLength(0);
                        }
                        if (!BRACKET.in(a[i + 1])) {
                            if (!lb) lb = true;
                            lb_c++;
                        } else {
                            tmp.append(current);
                            current.setLength(0);
                        }
                    }
                } else if (lb) {
                    if (OPERATOR.found(current)) {
                        if (!OPERATOR.isBracket(current)) {
                            if (!lb_r) {
                                if (!current.toString().equals(POW)) {
                                    if (i != a.length - 1) {
                                        if (!BRACKET.in(a[i + 1])) {
                                            tmp.append(RIGHT_BRACKET);
                                            lb_c--;
                                            rb = true;

                                        }
                                        if (!rb && BRACKET.in(a[i + 1])) {
                                            if (lb_i > -1) {
                                                tmp.deleteCharAt(lb_i);
                                                lb_c--;
                                            }
                                        }
                                    }
                                    while (lb_c > 0) {
                                        tmp.append(RIGHT_BRACKET);
                                        lb_c--;
                                    }
                                    result.append(tmp).append(current);
                                    tmp.setLength(0);
                                    lb = false;
                                }
                            }
                        } else {
                            if (current.toString().equals(LEFT_BRACKET)) {
                                lb_r = true; rb_r = false;
                            }
                            if (current.toString().equals(RIGHT_BRACKET)) {
                                if (rb_r)  {
                                    if (lb_i > -1 && lb_i < tmp.length()) {
                                        tmp.deleteCharAt(lb_i);
                                        lb_c--;
                                    }
                                    while (lb_c > 0) {
                                        tmp.append(RIGHT_BRACKET);
                                        lb_c--;
                                    }
                                }
                                result.append(tmp).append(current);
                                tmp.setLength(0);
                                rb_r = true; lb_r = false;
                            }
                        }
                        if (!rb && lb && !rb_r) {
                            tmp.append(current);
                        }
                        if (rb) rb = false;
                        current.setLength(0);
                    } else {
                        if (i == a.length - 1) {
                            result.append(tmp.deleteCharAt(lb_i))
                                    .append(current).append(RIGHT_BRACKET);
                            return result.toString();
                        } else  {
                            if (BRACKET.in(a[i + 1])) {
                                tmp.deleteCharAt(lb_i);
                                lb_c--;
                                result.append(tmp).append(current);
                                tmp.setLength(0);
                            } else {
                                tmp.append(current);
                            }
                            current.setLength(0);
                        }
                    }
                } else {
                    result.append(current);
                    current.setLength(0);
                }
            }
        }
        return result.toString();
    }*/
}
