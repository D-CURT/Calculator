package beans;

import beans.abstractions.AbstractElement;
import interfaces.FI_Element_found;
import support.constants.Constants;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.OPERAND;
import static support.constants.Constants.OPERATOR;
import static support.constants.Constants.POINT;

public class Element extends AbstractElement {
    public enum Content {
        OPERAND(Constants.OPERAND::found),
        OPERATOR(Constants.OPERATOR::found);

        private final FI_Element_found method;

        Content(FI_Element_found method) {
            this.method = method;
        }

        /*public FI_Element_found getMethod() {
            return method;
        }*/
    }

    @Override
    public Content getElement(String s) {
        return Arrays.stream(Content.values())
                     .filter(element -> Content.OPERAND.method.found(s) || Content.OPERATOR.method.found(s))
                     .findFirst()
                     .orElse(null);
    }

    @Override
    public boolean found(String s) {
        return getElement(s).method != null;
    }

    /*public String readElement(char[] chars, int iterator) {
        return readElement(chars, iterator, getType(chars[iterator]));
    }*/

    private String readElement(char[] chars, int iterator, Class<?> o) {
        StringBuilder element = new StringBuilder();
        while (getType(chars[iterator]) == o) {
            element.append(chars[iterator]);
            iterator++;
            if (o == Operator.class || iterator == chars.length) break;
        }
        return element.toString();
    }

    public List<String> asElementsList(String s) {
        List<String> list = new LinkedList<>();
        if (!s.isEmpty()) {
            Class<?> t;
            char[] a = s.toCharArray();
            for (int i = 0; i < a.length; i++) {
                t = getType(a[i]);
                list.add(readElement(a, i, t));
                while (getType(a[i]) == t) {
                    i++;
                    if (t == Operator.class || i == a.length) break;
                }
                i--;
            }
        }
        return list;
    }

    private Class<?> getType(char c) {
        return getType(String.valueOf(c));
    }

    private Class<?> getType(String s) {
        return (OPERATOR.getElement(s).getSymbol().equals(s)) ? Operator.class
                : (OPERAND.getElement(s).getMethod() != null) ? Operand.class
                : POINT.equals(s) ? Operand.class : null;
    }
}
