package abstractions;

import beans.Result;
import exceptions.CalculatorException;
import exceptions.OperationNotFoundException;
import interfaces.I_MultipleCalculator;
import services.ReversePolishNotationBuilder;

import java.util.ArrayDeque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static support.constants.Constants.FUNCTION;
import static support.constants.Constants.OPERATOR;


public abstract class AbstractCalculator implements I_MultipleCalculator {
    @Override
    public Result calculate(String expression) {
        ArrayDeque<Double> numbers = new ArrayDeque<>();
        try {
            String rpn = new ReversePolishNotationBuilder().toRPN(expression);
            double result;
            double first;
            double second;

            StringTokenizer tokenizer = new StringTokenizer(rpn);
            String element;

            while (tokenizer.hasMoreTokens()) {
                element = tokenizer.nextToken().trim();
                if (OPERATOR.found(element)) {
                    second = numbers.pop();
                    first = numbers.pop();
                    result = OPERATOR.getElement(element).count(first, second);
                } else if (FUNCTION.found(element)) {
                    first = numbers.pop();
                    result = FUNCTION.getElement(element).count(first);
                } else result = Double.parseDouble(element);
                numbers.push(result);
            }
            if (numbers.size() > 1) throw new CalculatorException("The number of operands is not agreed.");
        } catch (CalculatorException e) {
            return new Result(expression, e.getMessage());
        }
        return new Result(expression, numbers.pop());
    }

    @Override
    public List<Result> calculate(List<String> expressions) {
        return expressions.stream()
                          .map(this::calculate)
                          .collect(Collectors.toList());
    }
}
