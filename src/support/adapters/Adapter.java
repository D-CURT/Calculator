package support.adapters;

import exceptions.CalculatorException;

import static support.constants.Constants.*;

public class Adapter {
    private static final PowAdapter POW_ADAPTER = new PowAdapter();
    private static final FunctionAdapter FUNCTION_ADAPTER = new FunctionAdapter();

    public String adapt(String s) {
        if (OPERATOR.bracket.areBracketsAgreed(s)) {
            s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
            s = FUNCTION_ADAPTER.replaceUnaryMinus(ELEMENT.asElementsList(s));
            s = FUNCTION_ADAPTER.adaptFunctions(s);
            s = POW_ADAPTER.setPriority(s);
            System.out.println("Adapted: " + s);
        }
        return s;
    }
}
