package support.comparators;

import bean.Operator;

import java.util.Comparator;

public class CharPriority implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        Operator o1 = Operator.find(s1);
        Operator o2 = Operator.find(s2);
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
