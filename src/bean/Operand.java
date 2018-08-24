package bean;

import bean.interfaces.IOperandChecker;

import java.util.regex.Pattern;

import static support.constants.Constants.FRACTIONAL_REGEX;
import static support.constants.Constants.INTEGER_REGEX;

public enum Operand {
    INTEGER(s -> Pattern.compile(INTEGER_REGEX).matcher(s).find()),
    FRACTIONAL(s -> Pattern.compile(FRACTIONAL_REGEX).matcher(s).find());

    private IOperandChecker method;

    Operand(IOperandChecker method) {
        this.method = method;
    }

    public static boolean isOperand(String s) {
        return INTEGER.method.check(s) || FRACTIONAL.method.check(s);
    }

    public IOperandChecker getMethod() {
        return method;
    }
}
