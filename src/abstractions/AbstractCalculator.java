package abstractions;

import beans.Result;
import interfaces.I_MultipleCalculator;
import services.ReversePolishNotationBuilder;

import java.util.ArrayDeque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public abstract class AbstractCalculator implements I_MultipleCalculator {
    @Override
    public Result calculate(String expression) {
        ArrayDeque<Double> results = new ArrayDeque<>();
        try {
            String rpn = new ReversePolishNotationBuilder().toRPN(expression);
            double result;
            double first;
            double second;

            StringTokenizer tokenizer = new StringTokenizer(rpn);
            String element;

            while (tokenizer.hasMoreTokens()) {
                element = tokenizer.nextToken().trim();
            }
        } catch (Exception e) {
            return new Result(expression, e.getMessage());
        }
        return new Result(expression, results.pop());
    }

    @Override
    public List<Result> calculate(List<String> expressions) {
        return expressions.stream()
                          .map(this::calculate)
                          .collect(Collectors.toList());
    }
}
