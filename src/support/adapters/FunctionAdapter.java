package support.adapters;

import beans.elements.Function;

import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.*;
import static support.constants.Constants.LEFT_BRACKET;
import static support.constants.Constants.RIGHT_BRACKET;

class FunctionAdapter {
    private boolean opened = false;
    private boolean closed = false;

    /*It has to add possibility to handle functions in another functions*/
    String adaptFunctions(String s) {
        LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(s));
        final int END = a.size() - 1;
        int lb_c = 0;
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
            if (opened) {
                result.append(current).append(LEFT_BRACKET);
                lb_c++;
            }
            else if (closed) {
                result.append(current);
                while (lb_c > 0) {
                    result.append(RIGHT_BRACKET);
                    lb_c--;
                }
                closed = false;
            }
            else result.append(current);
        }
        return result.toString();
    }

    String replaceUnaryMinus(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Function.um.setUnaryMinus(list, i);
            builder.append(list.get(i));
        }
        return builder.toString();
    }
}
