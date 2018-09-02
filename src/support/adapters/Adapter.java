package support.adapters;

import static support.constants.Constants.*;

public class Adapter {
    private static final PowAdapter POW_ADAPTER = new PowAdapter();
    private static final FunctionAdapter FUNCTION_ADAPTER = new FunctionAdapter();

    public String adapt(String s) {
        s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
        s = FUNCTION_ADAPTER.adaptFunctions(s);
        s = POW_ADAPTER.setPriority(s);
        return s;
    }
}
