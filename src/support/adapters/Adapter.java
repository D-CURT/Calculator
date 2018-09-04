package support.adapters;

import exceptions.CalculatorException;

import static support.constants.Constants.*;

public class Adapter {
    private static final PowAdapter POW_ADAPTER = new PowAdapter();
    private static final FunctionAdapter FUNCTION_ADAPTER = new FunctionAdapter();

    public String adapt(String s) {
        if (isBracketsAgreed(s)) {
            s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
            s = FUNCTION_ADAPTER.replaceUnaryMinus(ELEMENT.asElementsList(s));
            s = FUNCTION_ADAPTER.adaptFunctions(s);
            s = POW_ADAPTER.setPriority(s);
        }
        return s;
    }

    private boolean isBracketsAgreed(String s) {
        int check = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') check++;
            if (s.charAt(i) == ')') check--;
        }
        if (check != 0) throw new CalculatorException("Brackets are not agreed.");
        else return true;
    }
}
