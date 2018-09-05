package support.adapters;

import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.*;

class FunctionAdapter {
    private List<String> list;
    private int end;
    private int i;
    private boolean opened = false;
    private boolean closed = false;

    FunctionAdapter init(List<String> list) {
        this.list = list;
        end = list.size() - 1;
        return this;
    }

    /*It has to add possibility to handle functions in another functions*/
    String adaptFunctions() {
        //LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(s));
        //final int END = a.size() - 1;
        int lb_c = 0;
        String current;
        StringBuilder result = new StringBuilder();


        for (i = 0; i < list.size(); i++) {
            current = list.get(i);
            if (FUNCTION.found(current)) {
                if (i != end && !list.get(i + 1).equals(LEFT_BRACKET)) {
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

    String replaceUnaryMinus() {
        StringBuilder builder = new StringBuilder();
        for (i = 0; i < list.size(); i++) {
            FUNCTION.um.setUnaryMinus(list, i);
            builder.append(list.get(i));
        }
        return builder.toString();
    }
}
