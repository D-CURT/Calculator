package beans;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

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

    private boolean hasError() {
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

    private String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result1 = (Result) o;
        return Objects.equals(expression, result1.expression) &&
                Objects.equals(result, result1.result) &&
                Objects.equals(error, result1.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, result, error);
    }
}
