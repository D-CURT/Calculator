package support.adapters;

import exceptions.ElementsNotAgreedException;

import java.util.List;

import static support.constants.Constants.*;

class PowAdapter {
    private List<String> list;
    private int end;
    private int i;

    private static final String OPENED = "<";
    private static final String CLOSED = ">";

    private boolean bracket_UP = false;
    private boolean opened = false;
    private int count;

    PowAdapter init(List<String> list) {
        this.list = list;
        end = list.size() - 1;
        count = 0;
        return this;
    }

    private void toResult(StringBuilder tmp, StringBuilder result, String current) {
        if (!OPERATOR.found(current) && opened && i == end) {
            tmp.append(current);
            current = EMPTY;
            close(tmp);
        }
        result.append(tmp.length() > 0 ? tmp : EMPTY).append(current);
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

    private void open(StringBuilder tmp) {
        if (i != end) {
            String current = list.get(i);
            String next = list.get(i + 1);
            if (OPERAND.found(next) || FUNCTION.found(next))
                tmp.append(current).append(OPENED);
            if (next.equals(LEFT_BRACKET))
                tmp.append(current);
            else {
                if (OPERATOR.found(next)) throw new ElementsNotAgreedException();
                else {
                    count++;
                    if (!opened) opened = true;
                }
            }
        }
    }

    String setPriority(String input) {
        if (input.contains(POW)) {
            String current;
            StringBuilder result = new StringBuilder();
            StringBuilder tmp = new StringBuilder();
            for (i = 0; i < list.size(); i++) {
                current = list.get(i);
                if (current.equals(POW)) {
                    open(tmp);
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
                            if (OPERATOR.bracket.isBracket(list.get(i + 1))) {
                                if (tmp.lastIndexOf(OPENED) == tmp.length() - 1) {
                                    remove(tmp);
                                }
                                toResult(tmp, result, current);
                            } else tmp.append(current);
                        } else {
                            remove(tmp);
                            toResult(tmp, result, current);
                        }
                    }
                } else toResult(tmp, result, current);
            }
            if (result.length() > 0) {
                input = result.toString();
                input = input.replaceAll(OPENED, LEFT_BRACKET).replaceAll(CLOSED, RIGHT_BRACKET);
            } else throw new ElementsNotAgreedException();
        }
        return input;
    }
}
