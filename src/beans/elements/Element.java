package beans.elements;

import abstractions.AbstractElement;
import exceptions.OperationNotFoundException;
import interfaces.functional_interfaces.FI_Element_found;
import support.constants.Constants;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.*;

public class Element extends AbstractElement {
    public enum Content {
        OPERAND(Constants.OPERAND::found),
        OPERATOR(Constants.OPERATOR::found),
        FUNCTION(Constants.FUNCTION::found);

        private final FI_Element_found method;

        Content(FI_Element_found method) {
            this.method = method;
        }
    }

    @Override
    public Content getElement(String s) {
        return Arrays.stream(Content.values())
                     .filter(element ->
                                Content.OPERAND.method.found(s)
                             || Content.OPERATOR.method.found(s)
                             || Content.FUNCTION.method.found(s))
                     .findFirst()
                     .orElse(null);
    }

    @Override
    public boolean found(String s) {
        return getElement(s).method != null;
    }

    public List<String> asElementsList(String s) throws OperationNotFoundException {
        List<String> list = new LinkedList<>();
        if (!s.isEmpty()) {
            StringBuilder element = new StringBuilder();
            char[] a = s.toCharArray();
            int i = 0;
            Class<?> t;
            while (i < a.length) {
                t = getType(a[i]);
                while (getType(a[i]) == t) {
                    element.append(a[i]);
                    i++;
                    if (t == Operator.class || i == a.length) break;
                    if (FUNCTION.found(element)) break;
                }
                if (element.length() != 0) list.add(element.toString());
                element.setLength(0);
            }
        }
        return list;
    }

    private Class<?> getType(char c) {
        return getType(String.valueOf(c));
    }

    private Class<?> getType(String s) {
        return (OPERATOR.getElement(s).getSymbol().equals(s)) ? Operator.class :
               (OPERAND.getElement(s).getMethod() != null) ? Operand.class :
               (POINT.equals(s)) ? Operand.class :
               (FUNCTION.getElement(s).getValue().equals(s)) ? Function.class : null;
    }
}
