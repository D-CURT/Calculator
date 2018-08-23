package bean;

import bean.interfaces.IOperandChecker;

import java.util.regex.Pattern;

public enum Operand {
    INTEGER(Operand::isInteger),
    FRACTIONAL(Operand::isFractional);

    private IOperandChecker method;

    Operand(IOperandChecker method) {
        this.method = method;
    }

    public static boolean isOperand(String s) {
        return isInteger(s) || isFractional(s);
    }

    public static boolean isInteger(String s) {
        return Pattern.compile("^-?\\d+$").matcher(s).find();
    }

    public static boolean isFractional(String s) {
        return Pattern.compile("^-?\\d+\\.\\d+$").matcher(s).find();
    }

    public IOperandChecker getMethod() {
        return method;
    }
}
