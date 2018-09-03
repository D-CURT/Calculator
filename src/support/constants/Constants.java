package support.constants;

import beans.elements.Element;
import beans.elements.Function;
import beans.elements.Operand;
import beans.elements.Operator;
import support.adapters.Adapter;

public class Constants {
    public static final Adapter ADAPTER = new Adapter();

    public static final Element ELEMENT = new Element();
    public static final Operand OPERAND = new Operand();
    public static final Operator OPERATOR = new Operator();
    public static final Function FUNCTION = new Function();

    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String POINT = ".";

    public static final String LEFT_BRACKET = Operator.Content.LEFT_BRACKET.getSymbol();
    public static final String RIGHT_BRACKET = Operator.Content.RIGHT_BRACKET.getSymbol();
    public static final String MINUS = Operator.Content.MINUS.getSymbol();
    public static final String POW = Operator.Content.POW.getSymbol();

    public static final String UNARY_MINUS = Function.Content.UNARY_MINUS.getValue();

    public static final String INTEGER_REGEX = "^-?\\d+$";
    public static final String FRACTIONAL_REGEX = "^-?\\d+\\.\\d+$";
}
