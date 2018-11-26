package beans.elements.sub_elements;

import java.util.List;

import static support.constants.Constants.*;

public class UnaryMinusManager {
    public void setUnaryMinus(List<String> list, int i) {
        if (list != null) {
            String current = list.get(i);
            if (current.equals(MINUS))
                list.set(i, isUnaryMinus(list, i) ? UNARY_MINUS : current);
        }
    }

    private boolean isUnaryMinus(List<String> list, int i) {
        return isFirstElement(list, i) ||
               isFirstAfterBracket(list, i) ||
               isFirstAfterOperatorOrFunction(list, i);
    }

    private boolean isFirstElement(List<String> list, int i) {
        return list.size() != 1 && i == 0 && OPERAND.found(list.get(i + 1));
    }

    private boolean isFirstAfterBracket(List<String> list, int i) {
        return i != list.size() - 1 && i != 0 && list.get(i - 1).equals(LEFT_BRACKET) &&
                (OPERAND.found(list.get(i + 1)) || FUNCTION.found(list.get(i + 1)));
    }

    private boolean isFirstAfterOperatorOrFunction(List<String> list, int i) {
        return (i != list.size() - 1 && i != 0 && !list.get(i - 1).equals(RIGHT_BRACKET) &&
               (OPERATOR.found(list.get(i - 1)) || FUNCTION.found(list.get(i - 1)))) &&
               ((OPERAND.found(list.get(i + 1)) || FUNCTION.found(list.get(i + 1))) ||
               list.get(i + 1).equals(LEFT_BRACKET));
    }
}