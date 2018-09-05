package support.adapters;

import java.util.LinkedList;

import static support.constants.Constants.*;
import static support.constants.Constants.RIGHT_BRACKET;

class PowAdapter {
    private static final String OPENED = "<";
    private static final String CLOSED = ">";
    private int end;
    private int count;
    private boolean bracket_UP = false;
    private boolean opened = false;

    PowAdapter() {
        count = 0;
    }

    private void toResult(StringBuilder tmp, StringBuilder result, String current) {
        result.append(tmp).append(current);
        tmp.setLength(0);
    }

    private void close(StringBuilder tmp) {
        while (count > 0) {
            tmp.append(CLOSED);
            count--;
        }
    }

    private void remove(StringBuilder tmp) {
        if (tmp.lastIndexOf(OPENED) != -1) {
            tmp.deleteCharAt(tmp.lastIndexOf(OPENED));
            count--;
        }
    }

    private void open(LinkedList<String> list, int i, StringBuilder tmp) {
        if (i != end) {
            String current = list.get(i);
            String next = list.get(i + 1);
            if (OPERAND.found(next) || FUNCTION.found(next))
                tmp.append(current).append(OPENED);
            if (next.equals(LEFT_BRACKET))
                tmp.append(current);
            else {
                count++;
                if (!opened) opened = true;
            }
        }
    }

    String setPriority(String input) {
        if (input.contains(POW)) {
            LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(input));
            end = a.size() - 1;
            String current;
            StringBuilder result = new StringBuilder();
            StringBuilder tmp = new StringBuilder();

            for (int i = 0; i < a.size(); i++) {
                current = a.get(i);
                if (current.equals(POW)) {
                    open(a, i, tmp);
                } else if (opened) {
                    if (OPERATOR.found(current)) {
                        if (!OPERATOR.bracket.isBracket(current)) {
                            if (!bracket_UP) {
                                if (!current.equals(POW)) {
                                    remove(tmp);
                                    close(tmp);
                                    toResult(tmp, result, current);
                                }
                            }
                        } else {
                            if (current.equals(LEFT_BRACKET)) {
                                bracket_UP = true;
                            }
                            if (current.equals(RIGHT_BRACKET)) {
                                remove(tmp);
                                close(tmp);
                                toResult(tmp, result, current);
                                bracket_UP = false;
                            }
                        }
                        if (bracket_UP) tmp.append(current);
                    } else {
                        if (i != end) {
                            if (OPERATOR.bracket.isBracket(a.get(i + 1))) {
                                if (tmp.lastIndexOf(OPENED) == tmp.length() - 1) {
                                    remove(tmp);
                                }
                                toResult(tmp, result, current);
                            } else tmp.append(current);
                        } else {
                            remove(tmp);
                            tmp.append(current);
                            current = "";
                            close(tmp);
                            toResult(tmp, result, current);
                        }
                    }
                } else {
                    if (tmp.length() > 0) {
                        result.append(tmp);
                        tmp.setLength(0);
                    }
                    result.append(current);
                }
            }
            input = result.toString();
            input = input.replaceAll(OPENED, LEFT_BRACKET).replaceAll(CLOSED, RIGHT_BRACKET);
        }
        return input;
    }
}
