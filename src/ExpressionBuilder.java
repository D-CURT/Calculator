import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ExpressionBuilder {
    public List<String> getRPN(String s) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        PriorityQueue<String> operatorsQueue = new PriorityQueue<>(new OperatorsComparator());
        char character;
        for (int i = 0; i < s.length(); i++) {
            character = s.charAt(i);
            if (Character.isDigit(character)) {
                while (Character.isDigit(character)) {
                    current.append(character);
                    i++;
                    if (i == s.length()) break;
                    character = s.charAt(i);
                }
                list.add(current.toString());
                current.delete(0, current.length() - 1);
                i--;
            }
            if (isOperator(String.valueOf(character))) {
                String operator = String.valueOf(character);
                if (operator.equals("(")) {
                    operatorsQueue.add(operator);
                } else if (operator.equals(")")) {
                    operator = operatorsQueue.poll();
                    while (!operator.equals("(")) {
                        list.add(operator);
                        operator = operatorsQueue.poll();
                    }
                }
                if (!operatorsQueue.isEmpty()) {
                    if (getPriority(operator) <= getPriority(operatorsQueue.peek()))
                        list.add(operatorsQueue.poll());
                }
                operatorsQueue.add(operator);
            }
        }
        while (!operatorsQueue.isEmpty())
            list.add(operatorsQueue.poll());
        return list;
    }

    public boolean isOperator(String s) {
        if ("()+-*/^".indexOf(s) != -1) return true;
        return false;
    }
    
    private int getPriority(String s) {
        return new OperatorsComparable().compareTo(s);
    }
}
