package support;

import java.util.LinkedList;
import java.util.Objects;

import static support.constants.Constants.*;

public class Adapter {
    public static String adapt(String s) {
        return setPowPriority(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    private static String setPowPriority(String input) {
        if (input.contains(POW)) {
            LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(input));
            final int END = a.size() - 1;
            String current;
            StringBuilder result = new StringBuilder();
            StringBuilder tmp = new StringBuilder();

            boolean lb = false, lb_r = false, rb_r = true;
            boolean toResult = false, remove = false, close = false;
            int lb_c = 0;

            for (int i = 0; i < a.size(); i++) {
                current = a.get(i);
                if (current.equals(POW)) {
                    if (i != END) {
                        if (OPERAND.found(a.get(i + 1)))
                            tmp.append(current).append(LEFT_BRACKET);
                        if (!OPERATOR.isBracket(a.get(i + 1))) {
                            if (!lb) lb = true;
                            lb_c++;
                        } else tmp.append(current);
                    }
                } else if (lb) {
                    if (OPERATOR.found(current)) {
                        if (!OPERATOR.isBracket(current)) {
                            if (!lb_r) {
                                if (!current.equals(POW)) {
                                    if (i != END) {
                                        if (!OPERATOR.isBracket(a.get(i + 1))) {
                                            remove = true;
                                            toResult = true;
                                        }
                                    }
                                    close = true;
                                    lb = false;
                                }
                            }
                        } else {
                            if (current.equals(LEFT_BRACKET)) {
                                lb_r = true; rb_r = false;
                            }
                            if (current.equals(RIGHT_BRACKET)) {
                                if (rb_r)  {
                                    remove = true;
                                    close = true;
                                }
                                toResult = true;
                                rb_r = true; lb_r = false;
                            }
                        }
                        if (!toResult && !rb_r) tmp.append(current);
                    } else {
                        if (i != END) {
                            if (OPERATOR.isBracket(a.get(i + 1))) {
                                if (tmp.lastIndexOf(LEFT_BRACKET) == tmp.length() - 1) {
                                    remove = true;
                                }
                                toResult = true;
                            } else tmp.append(current);
                        } else {
                            remove = true;
                            toResult = true;
                        }
                    }
                } else {
                    if (tmp.length() > 0) {
                        result.append(tmp);
                        tmp.setLength(0);
                    }
                    result.append(current);
                }
                if (remove) {
                    if (tmp.lastIndexOf(LEFT_BRACKET) != -1) {
                        tmp.deleteCharAt(tmp.lastIndexOf(LEFT_BRACKET));
                        lb_c--;
                    }
                    remove = false;
                }
                if (close) {
                    while (lb_c > 0) {
                        tmp.append(RIGHT_BRACKET);
                        lb_c--;
                    }
                    close = false;
                }
                if (toResult) {
                    result.append(tmp).append(current);
                    tmp.setLength(0);
                    toResult = false;
                }
            }
            input = result.toString();
        }
        return input;
    }

    public static String handleBrackets(String s) {
        StringBuilder result = new StringBuilder();


        if (s.contains("()")) {
            LinkedList<String> l = new LinkedList<>(ELEMENT.asElementsList(s));

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
                    //noinspection ConstantConditions
                    tmp.subList(start + 1, stop);
                }
            }
            return result.toString();
        }
        return s;
    }
}
