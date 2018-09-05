package support.adapters;

import static support.constants.Constants.*;

public class Adapter {
    private static final PowAdapter POW_ADAPTER = new PowAdapter();
    private static final FunctionAdapter FUNCTION_ADAPTER = new FunctionAdapter();

    public String adapt(String s) {
        if (OPERATOR.bracket.areBracketsAgreed(s)) {
            s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
            s = FUNCTION_ADAPTER.init(ELEMENT.asElementsList(s)).replaceUnaryMinus();
            s = FUNCTION_ADAPTER.init(ELEMENT.asElementsList(s)).adaptFunctions();
            s = POW_ADAPTER.init(ELEMENT.asElementsList(s)).setPriority(s);
            System.out.println("Adapted: " + s);
        }
        return s;
    }
}
