import services.ReversePolishNotationBuilder;
import support.Adapter;

public class Runner {
    public static void main(String[] args) {
        //System.out.println(new ReversePolishNotationBuilder().getRPN("(8^3^(5+2)*5)/(1^3+(3*2-4^6)^2)"));
        /*String s = "56-((84^3^(5+2^(2^3))*5)/(1^(2+3)+(3*2-4^6^2^(3+4)/78*(6^3^4))^2))";
        String sa = Adapter.adapt(s);
        String sr = new ReversePolishNotationBuilder().toRPN(sa);
        System.out.println("Initial: " + s
                           + "\nAdapted: " + sa
                           + "\nReversed: " + sr);*/

        String s2 = "56-((84^3^(5+2^(2^3))*5)/(1^(2+3)+(3*2-4^6^2^(3+4)/78*(6^3^4))^2))";
        String sa2 = Adapter.adapt(s2);
        String sr2 = new ReversePolishNotationBuilder().toRPN(sa2);
        System.out.println("Initial: " + s2
                + "\nAdapted: " + sa2
                + "\nReversed: " + sr2);
    }
}
