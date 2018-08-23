package bean;

import bean.interfaces.I_isOperand;

import java.util.regex.Pattern;

public enum Operand {
    INTEGER(Operand::isInteger),
    FRACTIONAL(Operand::isFractional);

    private I_isOperand check;

    Operand(I_isOperand check) {
        this.check = check;
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

    public I_isOperand getCheck() {
        return check;
    }
}
