import services.Calculator;
import services.ReversePolishNotationBuilder;

import java.util.LinkedList;
import java.util.List;

import static support.constants.Constants.ADAPTER;

public class Runner {
    public static void main(String[] args) {
        String s = "";
        System.out.println("Initial: " + s);
        String result = new Calculator().calculate(s).getResult();
        System.out.println("Result: " + result);
    }
}