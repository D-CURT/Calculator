import java.util.*;

public class ExpressionBuilder {
    public List<String> getRPN(String s) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        ArrayDeque<String> operatorsQueue = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {

            if (isDelimeter(String.valueOf(s.charAt(i))))
                continue;

            if (Character.isDigit(s.charAt(i))) {
                while (!isOperator(String.valueOf(s.charAt(i))) && !isDelimeter(String.valueOf(s.charAt(i)))) {
                    /*current.append(s.charAt(i));*/
                    list.add(String.valueOf(s.charAt(i)));
                    i++;
                    if (i == s.length()) break;
                }
                /*list.add(current.toString());
                current.delete(0, current.length());*/
                /*i--;*/
            }
            if (isOperator(String.valueOf(s.charAt(i)))) {
                String operator; /*String.valueOf(s.charAt(i));*/
                if (s.charAt(i) == '(') {
                    operatorsQueue.push(String.valueOf(s.charAt(i)));
                } else if (s.charAt(i) == ')') {
                    operator = operatorsQueue.pop();
                    while (!operator.equals("(")) {
                        list.add(operator);
                        operator = operatorsQueue.pop();
                    }
                } else {
                    if (!operatorsQueue.isEmpty()) {
                        if (getPriority(String.valueOf(s.charAt(i))) <= getPriority(operatorsQueue.peek()))
                            list.add(operatorsQueue.pop());
                    }
                    operatorsQueue.push(String.valueOf(s.charAt(i)));
                }
            }
        }
        while (!operatorsQueue.isEmpty())
            list.add(operatorsQueue.pop());
        return list;
    }

    public boolean isOperator(String s) {
        if ("()+-*/^".indexOf(s) != -1) return true;
        return false;
    }

    public boolean isDelimeter(String s) {
        if (" ".indexOf(s) != -1) return true;
        return false;
    }
    
    private int getPriority(String s) {
        return new OperatorsComparable().compareTo(s);
    }
}
