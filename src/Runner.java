import services.ReversePolishNotationBuilder;
import support.Adapter;

public class Runner {
    public static void main(String[] args) {
        //System.out.println(new ReversePolishNotationBuilder().getRPN("(8^3^(5+2)*5)/(1^3+(3*2-4^6)^2)"));
        String s = "(84^3^(5+2)*5)/(1^(2+3)+(3*2-4^6^2^(3+4))^2)";
        System.out.println(Adapter.adapt(s));

    }
}
