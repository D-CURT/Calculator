package support.comparators;

import java.util.Comparator;

import static support.constants.Constants.OPERATOR;

public class ElementPriority implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(OPERATOR.getPriority(s1), OPERATOR.getPriority(s2));
    }
}
