import services.ReversePolishNotationBuilder;
import support.Adapter;

public class Runner {
    public static void main(String[] args) {
        String s = "56-((84^3^(5,2+2^(2^3))*5)/(1^(2+3)+(3*2-4^6^2^(3+4)/78*(6^3^4))^2))";
        String sa = Adapter.adapt(s);
        String sr = new ReversePolishNotationBuilder().toRPN(sa);
        System.out.println("Initial: " + s
                + "\nAdapted: " + sa
                + "\nReversed: " + sr);
    }
}
