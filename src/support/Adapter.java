package support;

import java.util.LinkedList;

import static support.constants.Constants.*;

public class Adapter {
    public String adapt(String s) {
        s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
        s = ADAPTER.adaptFunctions(s);
        s = ADAPTER.setPowPriority(s);
        return s;
    }

    public String adaptFunctions(String s) {
        LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(s));
        final int END = a.size() - 1;
        String current;
        StringBuilder result = new StringBuilder();

        boolean lb = false, rb = false;

        for (int i = 0; i < a.size(); i++) {
            current = a.get(i);
            if (FUNCTION.found(current)) {
                if (i != END && !a.get(i + 1).equals(LEFT_BRACKET)) {
                    lb = true;
                }
            } else if (lb) {
                if (OPERAND.found(current)) {
                    rb = true;
                    lb = false;
                }
            }
            if (lb) result.append(current).append(LEFT_BRACKET);
            else if (rb) {
                result.append(current).append(RIGHT_BRACKET);
                rb = false;
            }
            else result.append(current);
        }
        return result.toString();
    }

    private String setPowPriority(String input) {
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
                        if (OPERAND.found(a.get(i + 1)) || FUNCTION.found(a.get(i + 1)))
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
                                if (i == END) close = true;
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
                            close = true;
                            toResult = true;
                            tmp.append(current);
                            current = "";
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
                        if (lb_c == 0) lb = false;
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
}
