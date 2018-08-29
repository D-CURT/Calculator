package support;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.*;

public class Adapter {
    public static String adapt(String s) {
        return getPowPriority(s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY));
    }

    private static String getPowPriority(String s) {

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
