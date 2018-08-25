package support.constants;

import bean.Operand;
import bean.Operator;
import bean.interfaces.IOperandChecker;

public class Constants {
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String POINT = ".";

    public static final char LEFT_BRACKET = Operator.LEFT_BRACKET.getSymbol().charAt(0);
    public static final char RIGHT_BRACKET = Operator.RIGHT_BRACKET.getSymbol().charAt(0);

    public static final String INTEGER_REGEX = "^-?\\d+$";
    public static final String FRACTIONAL_REGEX = "^-?\\d+\\.\\d+$";

    public static final IOperandChecker INTEGER = Operand.INTEGER.getMethod();
    public static final IOperandChecker FRACTIONAL = Operand.FRACTIONAL.getMethod();

}
