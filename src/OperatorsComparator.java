import java.util.Comparator;

public class OperatorsComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int first = Integer.parseInt(o1);
        int second = Integer.parseInt(o2);
        return (first > second)? 1 : (first < second)? -1 : 0;
    }
}
