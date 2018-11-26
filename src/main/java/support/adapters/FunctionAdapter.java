package support.adapters;

import java.util.List;

import static support.constants.Constants.*;

class FunctionAdapter extends Adapter {
    private List<String> list;
    private int end;
    private int i;
    private boolean opened = false;
    private boolean closed = false;

    private void init(List<String> list) {
        this.list = list;
        end = list.size() - 1;
    }

    @Override
    public String adapt(String s) {
        int lb_c = 0;
        String current;
        StringBuilder result = new StringBuilder();
        init(ELEMENT.asElementsList(s));
        replaceUnaryMinus();


        for (i = 0; i < list.size(); i++) {
            current = list.get(i);
            if (FUNCTION.found(current)) {
                if (i != end && !list.get(i + 1).equals(LEFT_BRACKET))
                    opened = true;
            } else if (opened) {
                if (OPERAND.found(current)) {
                    closed = true;
                    opened = false;
                }
            }
            if (opened) {
                result.append(current).append(LEFT_BRACKET);
                lb_c++;
            } else if (closed) {
                result.append(current);
                while (lb_c > 0) {
                    result.append(RIGHT_BRACKET);
                    lb_c--;
                }
                closed = false;
            } else result.append(current);
        }
        return result.toString();
    }

     private void replaceUnaryMinus() {
        for (i = 0; i < list.size(); i++)
            FUNCTION.um.setUnaryMinus(list, i);
    }
}
