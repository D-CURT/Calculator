package beans;

import beans.abstractions.AbstractElement;
import interfaces.FI_Function_count;

import java.util.Arrays;

public class Function extends AbstractElement {
    public enum Content {
        SIN("sin", Math::sin),
        COS("cos", Math::cos),
        TG("tan", Math::tan),
        SQRT("sqrt", Math::sqrt),

        DEFAULT();

        private String value;
        private FI_Function_count<Double> function;

        Content() {
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
