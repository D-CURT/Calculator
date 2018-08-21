

public class Runner {
    public static void main(String[] args) {
        System.out.println(new ReversePolishNotationBuilder().getRPN("(8+2*5)/(1+3*2-4)"));
    }
}
