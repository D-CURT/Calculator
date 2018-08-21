import java.util.*;

public class ExpressionBuilder {
    public List<String> getRPN(String s) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        ArrayDeque<Character> operatorsQueue = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {

            if (Character.isDigit(s.charAt(i))) {
                while (!isOperator(s.charAt(i))) {
                    list.add(String.valueOf(s.charAt(i)));
                    i++;
                    if (i == s.length()) break;
                }
            }

            if (isOperator(s.charAt(i))) {
                Character operator;
                if (s.charAt(i) == '(') {
                    operatorsQueue.push(s.charAt(i));
                } else if (s.charAt(i) == ')') {
                    operator = operatorsQueue.pop();
                    while (!operatorsQueue.isEmpty() && operator != '(') {
                        list.add(operator.toString());
                        operator = operatorsQueue.pop();
                    }
                } else {
                    if (!operatorsQueue.isEmpty()) {
                        while (getPriority(String.valueOf(s.charAt(i))) <= getPriority(operatorsQueue.peek().toString()))
                            list.add(operatorsQueue.pop().toString());
                    }
                    operatorsQueue.push(s.charAt(i));
                }
            }

        }
        while (!operatorsQueue.isEmpty())
            list.add(operatorsQueue.pop().toString());
        return list;
    }

    public boolean isOperator(Character c) {
        return "()+-*/^".indexOf(c) != -1;
    }
    
    private static int getPriority(String s) {
        return new OperatorsComparable().compareTo(s);
    }
}
