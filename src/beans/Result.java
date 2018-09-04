package beans;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Result {
    private String expression;
    private Double result;
    private String error;

    public Result(String expression, Double result) {
        this.expression = expression;
        this.result = result;
    }

    public Result(String expression, String error) {
        this.expression = expression;
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public String getResult() {
        return expression + (hasError() ? " - " + getError() : "=" + formatResult());
    }

    private String formatResult() {
        String format;
        Locale locale = new Locale("en", "UK");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(locale);
        format = result % 1 == 0 ? "##0" : "##0.00000";
        DecimalFormat df = new DecimalFormat(format, dfs);
        return df.format(result);
    }

    public String getError() {
        return error;
    }
}
