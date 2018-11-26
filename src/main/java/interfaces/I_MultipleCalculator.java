package interfaces;

import beans.Result;

import java.util.List;

public interface I_MultipleCalculator extends I_Calculator {
    List<Result> calculate(List<String> expressions);
}
