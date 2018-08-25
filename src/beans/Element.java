package beans;

import beans.abstractions.AbstractElement;
import beans.interfaces.IElementType;
import support.constants.Constants;

import java.util.Arrays;

import static support.constants.Constants.OPERAND;
import static support.constants.Constants.OPERATOR;
import static support.constants.Constants.POINT;

public class Element extends AbstractElement {
    public enum Content {
        OPERAND(Constants.OPERAND::found),
        OPERATOR(Constants.OPERATOR::found);

        private final IElementType method;

        Content(IElementType method) {
            this.method = method;
        }

        public IElementType getMethod() {
            return method;
        }
    }

    @Override
    public Content getElement(String s) {
        return Arrays.stream(Content.values()).filter(element ->
                Content.OPERAND.method.found(s) || Content.OPERATOR.method.found(s)).findFirst().orElse(null);
    }

    @Override
    public boolean found(String s) {
        return getElement(s).method != null;
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
        return (OPERATOR.getElement(s).getSymbol().equals(s)) ? Operator.class
                : (OPERAND.getElement(s).getMethod() != null) ? Operand.class
                : POINT.equals(s) ? Operand.class : null;
    }
}
