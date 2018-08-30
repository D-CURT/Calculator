package support;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.*;

public class Adapter {
    public static String adapt(String s) {
        return setPowPriority(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    /*private static String getPowPriority(String s) {

        StringBuilder result = new StringBuilder();
        if (s.contains(POW)) {
            String current;
            char[] a = s.toCharArray();
            int lb_c = 0;
            boolean lb = false;


            for (int i = 0; i < a.length; i++) {
                current = ELEMENT.readElement(a, i, ELEMENT.getType(a[i]));
                if (OPERATOR.found(current)) {
                    if (current.equals(POW)) {
                        result.append(current).append(LEFT_BRACKET);
                        lb_c++;
                        if (!lb) lb = true;

                    } else if (lb) {
                        result.deleteCharAt(result.lastIndexOf(LEFT_BRACKET));
                        lb_c--;
                        while (lb_c > 0) {
                            result.append(RIGHT_BRACKET);
                            lb_c--;
                        }
                        lb = false;
                    }
                }
                if (!current.equals(POW)) result.append(current);
            }
            return result.toString();
        } else return s;
    }*/

    private static String setPowPriority(String input) {
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
                    if (tmp.length() > 0/*tmp.lastIndexOf(POW) != -1*/) {
                        result.append(tmp);
                        tmp.setLength(0);
                    }
                    result.append(current);
                    current.setLength(0);
                }
            }
        }
        return result.toString();
    }

    public static String handleBrackets(String s) {
        StringBuilder result = new StringBuilder();


        if (s.contains("()")) {
            LinkedList<String> l = new LinkedList<>(Arrays.asList(s.split("")));

            String current;
            LinkedList<String> tmp = null;
            int start = 0, stop = 0;

            for (int i = 0; i < l.size(); i++) {
                current = l.get(i);

                if (current.equals(LEFT_BRACKET)) {
                    start = i;
                }
                if (current.equals(RIGHT_BRACKET)) {
                    stop = i;
                    tmp.subList(start + 1, stop);

                }
            }

            return result.toString();
        }
        return s;
    }
}
