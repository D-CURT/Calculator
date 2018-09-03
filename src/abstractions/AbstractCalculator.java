package abstractions;

import beans.Result;
import interfaces.I_MultipleCalculator;
import services.ReversePolishNotationBuilder;

import java.util.ArrayDeque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static support.constants.Constants.OPERATOR;


public abstract class AbstractCalculator implements I_MultipleCalculator {
    @Override
    public Result calculate(String expression) {
        ArrayDeque<Double> operands = new ArrayDeque<>();
        try {
            String rpn = new ReversePolishNotationBuilder().toRPN(expression);
            double result;

            StringTokenizer tokenizer = new StringTokenizer(rpn);
            String element;

            while (tokenizer.hasMoreTokens()) {
                element = tokenizer.nextToken().trim();
            }
        } catch (Exception e) {
            return new Result(expression, e.getMessage());
        }
        return new Result(expression, operands.pop());
    }

    @Override
    public List<Result> calculate(List<String> expressions) {
        return expressions.stream()
                          .map(this::calculate)
                          .collect(Collectors.toList());
    }
}
