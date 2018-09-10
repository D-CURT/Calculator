package support.adapters;

import exceptions.CalculatorException;

import static support.constants.Constants.*;

public class Adapter {
    private static final PowAdapter POW_ADAPTER = new PowAdapter();
    private static final FunctionAdapter FUNCTION_ADAPTER = new FunctionAdapter();

    public String adapt(String s) {
        if (verifier(s)) {
            if (OPERATOR.bracket.areBracketsAgreed(s)) {
                s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
                s = FUNCTION_ADAPTER.adapt(s);
                s = POW_ADAPTER.adapt(s);
                System.out.println("Adapted: " + s);
            }
            return s;
        } throw new CalculatorException("The expression does not exist.");
    }

    private boolean verifier(String s) {
        return s != null && !s.equals(EMPTY);
    }
}
