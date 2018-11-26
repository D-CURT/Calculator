package exceptions;

public class ElementsNotAgreedException extends CalculatorException {
    public ElementsNotAgreedException() {
        super("The number of operands and operators is not agreed.");
    }
}
