import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ExpressionBuilder {
    public List<Character> getRPN(String s) {
        ArrayList<Character> list = new ArrayList<>();
        ArrayDeque<Character> operators = new ArrayDeque<>();
        PriorityComparator priority = new PriorityComparator();
        StringBuilder current = new StringBuilder();


        for (int i = 0; i < s.length(); i++) {

            if (Character.isDigit(s.charAt(i))) {
                while (!isOperator(s.charAt(i))) {
                    list.add(s.charAt(i));
                    i++;
                    if (i == s.length()) break;
                }
            }

            if (isOperator(s.charAt(i))) {
                Character operator;
                if (s.charAt(i) == '(') {
                    operators.push(s.charAt(i));
                } else if (s.charAt(i) == ')') {
                    operator = operators.pop();
                    while (operator != '(') {
                        list.add(operator);
                        operator = operators.pop();
                    }
                } else {
                    if (!operators.isEmpty()) {
                        while (priority.compare(s.charAt(i), operators.peek()) < 1)
                            list.add(operators.pop());
                    }
                    operators.push(s.charAt(i));
                }
            }

        }
        while (!operators.isEmpty())
            list.add(operators.pop());
        return list;
    }

    public boolean isOperator(Character c) {
        return "()+-*/^".indexOf(c) != -1;
    }
}
