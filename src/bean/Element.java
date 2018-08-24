package bean;

import bean.interfaces_and_abstracts.IElement;

import java.util.Arrays;

import static support.constants.Constants.POINT;
import static support.constants.Constants.SPACE;

public enum  Element {
    OPERAND(Operand.class ,Operand::typeOf),
    OPERATOR(Operator.class ,Operator::typeOf);

    private Class<?> type;
    private IElement method;

    Element(Class<?> type,IElement method) {
        this.type = type;
        this.method = method;
    }

    public static String readElement(char[] chars, int iterator, Class<?> o) {
        StringBuilder element = new StringBuilder();
        while (Element.getType(chars[iterator]) == o) {
            element.append(chars[iterator]);
            iterator++;
            if (iterator == chars.length) break;
        }
        return element.append(SPACE).toString();
    }

    public static Class<?> getType(char c) {
        return getType(String.valueOf(c));
    }

    public static Class<?> getType(String s) {
        return (Operator.find(s).getSymbol() == s.charAt(0)) ? Operator.class :
               (Operand.find(s).getMethod() != null) ? Operand.class : POINT.equals(s) ? Operand.class : null;
    }

    public static Element find(String s) {
        return Arrays.stream(values()).filter(element ->
                OPERAND.method.typeOf(s) || OPERATOR.method.typeOf(s)).findFirst().orElse(null);
    }

    public static boolean typeOf(char c) {
        return typeOf(String.valueOf(c));
    }

    public static boolean typeOf(String s) {
        return find(s).method != null;
    }
}
