package support;

import java.util.LinkedList;

import static support.constants.Constants.*;

public class Adapter {
    public String adapt(String s) {
        s = s.replaceAll(COMMA, POINT).replaceAll(SPACE, EMPTY);
        s = ADAPTER.adaptFunctions(s);
        s = ADAPTER.setPowPriority(s);
        return s;
    }

    private String adaptFunctions(String s) {
        LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(s));
        final int END = a.size() - 1;
        String current;
        StringBuilder result = new StringBuilder();

        boolean lb = false, rb = false;

        for (int i = 0; i < a.size(); i++) {
            current = a.get(i);
            if (FUNCTION.found(current)) {
                if (i != END && !a.get(i + 1).equals(LEFT_BRACKET)) {
                    lb = true;
                }
            } else if (lb) {
                if (OPERAND.found(current)) {
                    rb = true;
                    lb = false;
                }
            }
            if (lb) result.append(current).append(LEFT_BRACKET);
            else if (rb) {
                result.append(current).append(RIGHT_BRACKET);
                rb = false;
            }
            else result.append(current);
        }
        return result.toString();
    }

    private String setPowPriority(String input) {
        if (input.contains(POW)) {
            /*
            * Открытие приоритета:
            * 1. Тек. эл. '^'; НЕ конец строки; След. эл. цифра или функция.
            *   Увеличение счетчика:
            *   1.1 След. эл. НЕ скобка.
            *
            * Заполнение временной строки:
            * 1. НЕ конец строки; Тек. эл. '^'; След. эл. скобка.
            * 2. Приоритет открыт; Тек. эл. оператор; (флаг) Приоритет НЕ закрывается; (флаг) Скобка НЕ открыта.
            * 3. Тек. эл. НЕ оператор; След. эл. НЕ скобка.
            *
            * Удаление приоритета из временной строки:
            * 1. Открытый приоритет есть во врем. строке.
            *   Уменьшение счетчика:
            *   1.1 Приоритет удален.
            * 2. Тек. эл. оператор; НЕ '^'; НЕ скобка; (флаг) Результ. скобка НЕ открыта; НЕ конец строки; След. эл. НЕ скобка.
            * 3. Тек. эл. ')'; (флаг) Скобка закрыта.
            * 4. Тек. эл. оператор; Конец строки.
            *
            * */
            LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(input));
            final int END = a.size() - 1;
            final String OPEN = "<";
            final String CLOSE = ">";
            String current;
            StringBuilder result = new StringBuilder();
            StringBuilder tmp = new StringBuilder();

            boolean lb = false, lb_r = false, rb_r = true;
            boolean toResult = false, remove = false, close = false;
            int lb_c = 0;

            for (int i = 0; i < a.size(); i++) {
                current = a.get(i);
                if (current.equals(POW)) {
                    if (i != END) {
                        if (OPERAND.found(a.get(i + 1)) || FUNCTION.found(a.get(i + 1)))
                            tmp.append(current).append(OPEN);
                        if (!OPERATOR.isBracket(a.get(i + 1))) {
                            if (!lb) lb = true;
                            lb_c++;
                        } else tmp.append(current);
                    }
                } else if (lb) {
                    if (OPERATOR.found(current)) {
                        if (!OPERATOR.isBracket(current)) {
                            if (!lb_r) {
                                if (!current.equals(POW)) {
                                    if (i != END) {
                                        if (!OPERATOR.isBracket(a.get(i + 1))) {
                                            remove = true;
                                            toResult = true;
                                        }
                                    }
                                    close = true;
                                    lb = false;
                                }
                            }
                        } else {
                            if (current.equals(LEFT_BRACKET)) {
                                lb_r = true; rb_r = false;
                            }
                            if (current.equals(RIGHT_BRACKET)) {
                                if (rb_r)  {
                                    remove = true;
                                    close = true;
                                }
                                if (i == END) close = true;
                                toResult = true;
                                rb_r = true; lb_r = false;
                            }
                        }
                        if (!toResult && !rb_r) tmp.append(current);
                    } else {
                        if (i != END) {
                            if (OPERATOR.isBracket(a.get(i + 1))) {
                                if (tmp.lastIndexOf(OPEN) == tmp.length() - 1) {
                                    remove = true;
                                }
                                toResult = true;
                            } else tmp.append(current);
                        } else {
                            remove = true;
                            close = true;
                            toResult = true;
                            tmp.append(current);
                            current = "";
                        }
                    }
                } else {
                    if (tmp.length() > 0) {
                        result.append(tmp);
                        tmp.setLength(0);
                    }
                    result.append(current);
                }
                if (remove) {
                    if (tmp.lastIndexOf(OPEN) != -1) {
                        tmp.deleteCharAt(tmp.lastIndexOf(OPEN));
                        lb_c--;
                        if (lb_c == 0) lb = false;
                    }
                    remove = false;
                }
                if (close) {
                    while (lb_c > 0) {
                        tmp.append(CLOSE);
                        lb_c--;
                    }
                    close = false;
                }
                if (toResult) {
                    result.append(tmp).append(current);
                    tmp.setLength(0);
                    toResult = false;
                }
            }
            input = result.toString();
            input = input.replaceAll(OPEN, LEFT_BRACKET).replaceAll(CLOSE, RIGHT_BRACKET);
        }
        return input;
    }
}
