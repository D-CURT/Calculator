package support.adapters;

import java.util.LinkedList;

import static support.constants.Constants.*;
import static support.constants.Constants.RIGHT_BRACKET;

class PowAdapter {
    private static final String OPENED = "<";
    private static final String CLOSED = ">";
    private int count;
    private boolean bracket_UP = false;
    private boolean bracket_DOWN = true;
    private boolean opened = false;

    String setPriority(String s) {
        if (s.contains(POW)) {
            LinkedList<String> list = new LinkedList<>(ELEMENT.asElementsList(s));
            final int END = list.size() - 1;


            
            String current;
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                current = list.get(i);
                if (OPERATOR.found(current)) {
                    if (opened) {

                    } else {
                        if (current.equals(POW) && i != END) {
                            open(list, i, result);
                        }
                    }
                }
            }
        }
        return s;
    }

    private void open(LinkedList<String> list, int i, StringBuilder result) {
        String current = list.get(i);
        String next = list.get(i + 1);
        if (OPERAND.found(next) || FUNCTION.found(next))
            result.append(current).append(OPENED);
        if (next.equals(LEFT_BRACKET))
            result.append(current);
        else {
            count++;
            if (!opened) opened = true;
        }
    }

    String setPowPriority(String input) {
        if (input.contains(POW)) {
            /*
             * Открытие приоритета:
             * 1. Тек. эл. '^'; НЕ конец строки; След. эл. цифра или функция.
             *   Увеличение счетчика:
             *   1.1 След. эл. НЕ скобка.
             *
             * Заполнение временной строки:
             * 1. НЕ конец строки; Тек. эл. '^'; След. эл. скобка.
             * 2. Тек. эл. оператор; Приоритет открыт; (флаг) Приоритет НЕ закрывается; (флаг) Скобка НЕ открыта.
             * 3. Тек. эл. НЕ оператор; Приоритет открыт; След. эл. НЕ скобка.
             *
             * Удаление приоритета из временной строки:
             * 1. Открытый приоритет есть во врем. строке.
             *   Уменьшение счетчика:
             *   1.1 Приоритет удален.
             * 2. Тек. эл. оператор; Приоритет открыт; НЕ '^'; НЕ скобка; (флаг) Скобка НЕ открыта; НЕ конец строки; След. эл. НЕ скобка.
             * 3. Тек. эл. ')'; Приоритет открыт; (флаг) Скобка закрыта.
             * 4. Тек. эл. оператор; Приоритет открыт; Конец строки.
             *
             * Закрытие приоритета:
             * 1. Приоритет открыт.
             * 2. Тек. эл. оператор; Приоритет открыт; НЕ '^'; Скобка НЕ открыта.
             * 3. Тек. эл. ')'; Приоритет открыт; Скобка НЕ открыта.
             * 4. Тек. эл. ')'; Приоритет открыт; Конец строки.
             * 5. Тек. эл. НЕ оператор; Приоритет открыт; Конец строки.
             *
             * Добавление в результат:
             * 1. Тек. эл. оператор; Приоритет открыт; НЕ '^'; НЕ '('; (флаг) Скобка НЕ открыта; НЕ конец строки; След. эл. НЕ скобка.
             * 2. Тек. эл. оператор; Приоритет открыт; Тек. эл. ')'.
             * 3. Тек. эл. НЕ оператор; Приоритет открыт; След. эл. скобка.
             * 4. Тек. эл. НЕ оператор; Приоритет открыт; Конец строки.
             *   Тек. эл. в результат:
             *   4.1. Тек. эл. НЕ '^'; Приоритет закрыт.
             *   Временная строка в результат:
             *   4.2. Временная строка не пуста; Приоритет закрыт.
             * */
            LinkedList<String> a = new LinkedList<>(ELEMENT.asElementsList(input));
            final int END = a.size() - 1;
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
                            tmp.append(current).append(OPENED);
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
                                if (tmp.lastIndexOf(OPENED) == tmp.length() - 1) {
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
                    if (tmp.lastIndexOf(OPENED) != -1) {
                        tmp.deleteCharAt(tmp.lastIndexOf(OPENED));
                        lb_c--;
                        if (lb_c == 0) lb = false;
                    }
                    remove = false;
                }
                if (close) {
                    while (lb_c > 0) {
                        tmp.append(CLOSED);
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
            input = input.replaceAll(OPENED, LEFT_BRACKET).replaceAll(CLOSED, RIGHT_BRACKET);
        }
        return input;
    }

}
