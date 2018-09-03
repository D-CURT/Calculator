package support.adapters;

import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.*;
import static support.constants.Constants.LEFT_BRACKET;
import static support.constants.Constants.RIGHT_BRACKET;

class FunctionAdapter {
    private boolean opened = false;
    private boolean closed = false;

    String adaptFunctions(String s) {
        LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(s));
        final int END = a.size() - 1;
        String current;
        StringBuilder result = new StringBuilder();


        for (int i = 0; i < a.size(); i++) {
            current = a.get(i);
            if (FUNCTION.found(current)) {
                if (i != END && !a.get(i + 1).equals(LEFT_BRACKET)) {
                    opened = true;
                }
            } else if (opened) {
                if (OPERAND.found(current)) {
                    closed = true;
                    opened = false;
                }
            }
            if (opened) result.append(current).append(LEFT_BRACKET);
            else if (closed) {
                result.append(current).append(RIGHT_BRACKET);
                closed = false;
            }
            else result.append(current);
        }
        return result.toString();
    }

    List<String> replaceUnaryMinus(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            FUNCTION.setUnaryMinus(list, i);
        }
        return list;
    }
}
