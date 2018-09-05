package beans.elements;

import abstractions.AbstractElement;
import interfaces.functional_interfaces.FI_Function_count;
import beans.elements.sub_elements.UnaryMinusManager;

import java.util.Arrays;

public class Function extends AbstractElement {
    public final UnaryMinusManager um = new UnaryMinusManager();

    public enum Content {
        SIN("sin", n -> Math.sin(Math.toRadians(n))),
        COS("cos", n -> Math.cos(Math.toRadians(n))),
        TAN("tan", n -> Math.tan(Math.toRadians(n))),
        SQRT("sqrt", Math::sqrt),
        UNARY_MINUS("um", n -> n = -n),

        DEFAULT();

        private String value;
        private FI_Function_count<Double> function;

        Content() {
            value = "";
        }

        Content(String value, FI_Function_count<Double> function) {
            this.value = value;
            this.function = function;
        }

        public Double count(Double n) {
            return function.count(n);
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public Content getElement(String s) {
        return Arrays.stream(Content.values())
                     .filter(Content -> Content.value != null && Content.value.equals(s))
                     .findFirst()
                     .orElse(Content.DEFAULT);
    }

    public boolean found(StringBuilder sb) {
        return found(sb.toString());
    }

    @Override
    public boolean found(String s) {
        return getElement(s).value.equals(s);
    }
}
