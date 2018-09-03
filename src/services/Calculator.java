package services;

import abstractions.AbstractCalculator;
import beans.Result;

import java.util.List;

public class Calculator extends AbstractCalculator {
    @Override
    public Result calculate(String expression) {
        return super.calculate(expression);
    }

    @Override
    public List<Result> calculate(List<String> expressions) {
        return super.calculate(expressions);
    }
}
