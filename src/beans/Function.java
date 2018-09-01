package beans;

import beans.abstractions.AbstractElement;
import interfaces.FI_Function_count;

import java.util.Arrays;

public class Function extends AbstractElement {
    public enum Content {
        SIN("sin", n -> Math.sin(Math.toRadians(n))),
        COS("cos", n -> Math.cos(Math.toRadians(n))),
        TG("tan", n -> Math.tan(Math.toRadians(n))),
        SQRT("sqrt", Math::sqrt),

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

    @Override
    public boolean found(String s) {
        return getElement(s).value.equals(s);
    }
}
