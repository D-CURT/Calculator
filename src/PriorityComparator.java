import java.util.Comparator;

public class PriorityComparator implements Comparator<Character> {

    @Override
    public int compare(Character o1, Character o2) {
        Priority p = new Priority();
        int first = p.compareTo(o1);
        int second = p.compareTo(o2);
        return Integer.compare(first, second);
    }

}
