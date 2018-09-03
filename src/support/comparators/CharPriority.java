package support.comparators;

import beans.elements.Operator;

import java.util.Comparator;

import static support.constants.Constants.OPERATOR;

public class CharPriority implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        Operator.Content o1 = OPERATOR.getElement(s1);
        Operator.Content o2 = OPERATOR.getElement(s2);
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
