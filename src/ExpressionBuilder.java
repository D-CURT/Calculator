import java.util.ArrayList;
import java.util.List;

public class ExpressionBuilder {
    public List<String> getRPN(String s) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        char character;
        for (int i = 0; i < s.length(); i++) {
            character = s.charAt(i);
            if (Character.isDigit(character)) {
                current.append(character);
            }
            if (!current.equals("")) list.add(current.toString());
        }
        return list;
    }
}
