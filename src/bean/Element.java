package bean;

import bean.interfaces.IElementType;

import java.util.Arrays;

import static support.constants.Constants.POINT;

public enum Element {
   /* OPERAND(Operand::isOperand),
    OPERATOR(Operator::isOperator);

    private final IElementType method;

    Element(IElementType method) {
        this.method = method;
    }

    public static String readElement(char[] chars, int iterator, Class<?> o) {
        StringBuilder element = new StringBuilder();
        while (getType(chars[iterator]) == o) {
            element.append(chars[iterator]);
            if (iterator != chars.length && o != Operator.class) iterator++;
            else break;
        }
        return element.toString();
    }

    public static Class<?> getType(char c) {
        return getType(String.valueOf(c));
    }

    public static Class<?> getType(String s) {

        return (Operator.find(s).getSymbol().equals(s)) ? Operator.class
                : (Operand.find(s).getMethod() != null) ? Operand.class
                : POINT.equals(s) ? Operand.class : null;
    }

    public static Element find(StringBuilder sb) {
        return find(sb.toString());
    }

    public static Element find(String s) {
        return Arrays.stream(values()).filter(element ->
                OPERAND.method.typeOf(s) || OPERATOR.method.typeOf(s)).findFirst().orElse(null);
    }

    public static boolean isElement(char c) {
        return isElement(String.valueOf(c));
    }

    public static boolean isElement(String s) {
        return find(s).method != null;
    }

    public IElementType getMethod() {
        return method;
    }*/
}
