import services.ReversePolishNotationBuilder;

public class Runner {
    public static void main(String[] args) {
        System.out.println(new ReversePolishNotationBuilder().getRPN("(8^a3^5+2*5)/(1^3+(3*2-4^6)^2)"));
    }
}
