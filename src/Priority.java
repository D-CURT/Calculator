public class Priority implements Comparable<Character> {
    @Override
    public int compareTo(Character o) {
        switch (o) {
            case '(': return 0;
            case ')': return 1;
            case '+': return 2;
            case '-': return 2;
            case '*': return 3;
            case '/': return 3;
            case '^': return 4;
        }
        return 5;
    }
}
