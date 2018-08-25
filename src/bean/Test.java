package bean;

import bean.interfaces.IElementType;

import java.util.Arrays;

import static support.constants.Constants.POINT;

public class Test {
   public enum Element {
        OPERAND(Operand::isOperand),
        OPERATOR(Operator::isOperator);

        private final IElementType method;

        Element(IElementType method) {
            this.method = method;
        }

        public IElementType getMethod() {
            return method;
        }
    }

    public String readElement(char[] chars, int iterator, Class<?> o) {
        StringBuilder element = new StringBuilder();
        while (getType(chars[iterator]) == o) {
            element.append(chars[iterator]);
            iterator++;
            if (o == Operator.class || iterator == chars.length) break;
        }
        return element.toString();
    }

    public Class<?> getType(char c) {
        return getType(String.valueOf(c));
    }

    public Class<?> getType(String s) {

        return (Operator.find(s).getSymbol().equals(s)) ? Operator.class
                : (Operand.find(s).getMethod() != null) ? Operand.class
                : POINT.equals(s) ? Operand.class : null;
    }

    public Test.Element find(StringBuilder sb) {
        return find(sb.toString());
    }

    public boolean isElement(char c) {
        return isElement(String.valueOf(c));
    }
    public boolean isElement(String s) {
        return find(s).method != null;
    }

    public Test.Element find(String s) {
        return Arrays.stream(Test.Element.values()).filter(element ->
                Test.Element.OPERAND.method.typeOf(s) || Test.Element.OPERATOR.method.typeOf(s)).findFirst().orElse(null);
    }
}
