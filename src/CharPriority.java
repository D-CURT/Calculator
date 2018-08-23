import java.util.Comparator;

public class CharPriority implements Comparator<Character> {

    @Override
    public int compare(Character c1, Character c2) {
        Operator o1 = Operator.find(c1);
        Operator o2 = Operator.find(c2);
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
