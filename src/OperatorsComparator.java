import java.util.Comparator;

public class OperatorsComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        OperatorsComparable comparable = new OperatorsComparable();
        int first = comparable.compareTo(o1);
        int second = comparable.compareTo(o2);
        return (first > second)? 1 : (first < second)? -1 : 0;
    }
}
