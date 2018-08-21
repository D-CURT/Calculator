public class OperatorsComparable implements Comparable<String> {
    @Override
    public int compareTo(String o) {
        switch (o) {
            case "(": return 0;
            case ")": return 1;
            case "+": return 2;
            case "-": return 2;
            case "*": return 3;
            case "/": return 3;
            case "^": return 4;
        }
        return 5;
    }
}
