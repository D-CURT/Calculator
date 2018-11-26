package beans.elements;

import interfaces.I_Element;
import interfaces.functional_interfaces.FI_Element_found;

import java.util.Arrays;
import java.util.regex.Pattern;

import static support.constants.Constants.FRACTIONAL_REGEX;
import static support.constants.Constants.INTEGER_REGEX;

public class Operand implements I_Element {
    public enum Content {
        INTEGER(s -> Pattern.compile(INTEGER_REGEX).matcher(s).find()),
        FRACTIONAL(s -> Pattern.compile(FRACTIONAL_REGEX).matcher(s).find()),

        DEFAULT();

        private FI_Element_found method;

        Content() {
        }

        Content(FI_Element_found method) {
            this.method = method;
        }

        public FI_Element_found getMethod() {
            return method;
        }
    }

    @Override
    public Content getElement(String s) {
        return Arrays.stream(Content.values())
                     .filter(operand -> Content.INTEGER.method.found(s) || Content.FRACTIONAL.method.found(s))
                     .findFirst()
                     .orElse(Content.DEFAULT);
    }

    @Override
    public boolean found(String s) {
        return getElement(s).method != null;
    }
}

