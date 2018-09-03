import services.ReversePolishNotationBuilder;

import static support.constants.Constants.ADAPTER;

public class Runner {
    public static void main(String[] args) {
        ReversePolishNotationBuilder polishNotationBuilder = new ReversePolishNotationBuilder();
        String s = "sin56-((84^3^(5,2+2^(2^sqrt3))*5)/-(1^cos(-2+3)+tan(3*2-4^6^2^(3+4)/78*(6^3^4))^2))";
        String sa = ADAPTER.adapt(s);
        String sr = polishNotationBuilder.toRPN(sa);
        System.out.println("Initial: " + s
                + "\nAdapted: " + sa
                + "\nReversed: " + sr);
    }
}