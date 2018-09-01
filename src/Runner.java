import services.ReversePolishNotationBuilder;

import static support.constants.Constants.ADAPTER;

public class Runner {
    public static void main(String[] args) {
        ReversePolishNotationBuilder polishNotationBuilder = new ReversePolishNotationBuilder();
        String s = "56-((84^3^(5,2+2^(2^3))*5)/(1^(2+3)+(3*2-4^6^2^(3+4)/78*(6^3^4))^2))";
        String sa = ADAPTER.adapt(s);
        String sr = polishNotationBuilder.toRPN(sa);
        System.out.println("Initial: " + s
                + "\nAdapted: " + sa
                + "\nReversed: " + sr);
        System.out.println(ADAPTER.adaptFunctions("sin5"));
        //System.out.println(polishNotationBuilder.toRPN("2+5+sin(5-4)"));
    }
}