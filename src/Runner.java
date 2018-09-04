import services.Calculator;
import services.ReversePolishNotationBuilder;

import static support.constants.Constants.ADAPTER;

public class Runner {
    public static void main(String[] args) {
        ReversePolishNotationBuilder polishNotationBuilder = new ReversePolishNotationBuilder();
        String s = "+3^2^2^2/1000-40000.021";
        System.out.println("Initial: " + s);
        /*String sa = ADAPTER.adapt(s);
        System.out.println("Adapted: " + sa);
        String sr = polishNotationBuilder.toRPN(s);
        System.out.println("Reversed: " + sr);*/
        String result = new Calculator().calculate(s).getResult();
        System.out.println("Result: " + result);
    }
}