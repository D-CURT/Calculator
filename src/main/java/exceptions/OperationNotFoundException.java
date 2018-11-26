package exceptions;

public class OperationNotFoundException extends CalculatorException {
    public OperationNotFoundException(String element) {
        super("The operation not found: [" + element + "]");
    }
}
