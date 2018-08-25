package bean;

import bean.interfaces.IOperandChecker;

import java.util.Arrays;
import java.util.regex.Pattern;

import static support.constants.Constants.FRACTIONAL_REGEX;
import static support.constants.Constants.INTEGER_REGEX;

//Нужно доработать структуру перечисления...
public enum Operand {
    INTEGER(s -> Pattern.compile(INTEGER_REGEX).matcher(s).find()),
    FRACTIONAL(s -> Pattern.compile(FRACTIONAL_REGEX).matcher(s).find()),

    DEFAULT();

    private IOperandChecker method;

    Operand() {
    }

    Operand(IOperandChecker method) {
        this.method = method;
    }

    public static Operand find(String s) {
        return Arrays.stream(values()).filter(operand ->
                INTEGER.method.check(s) || FRACTIONAL.method.check(s)).findFirst().orElse(DEFAULT);
    }

    public static boolean typeOf(char c) {
        return typeOf(String.valueOf(c));
    }

    public static boolean typeOf(String s) {
        return find(s).method != null;
    }

    public IOperandChecker getMethod() {
        return method;
    }
}
