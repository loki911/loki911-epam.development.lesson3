package com.epam.development.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Constructor {

    public static List<Expression> expAnalyze(String strInput) {
        ArrayList<Expression> symbols = new ArrayList<>();
        int pos = 0;
        while (pos < strInput.length()) {
            char mean = strInput.charAt(pos);
            switch (mean) {
                case '(':
                    symbols.add(new Expression(SignType.LEFT_BRACKET, mean));
                    pos++;
                    continue;
                case ')':
                    symbols.add(new Expression(SignType.RIGHT_BRACKET, mean));
                    pos++;
                    continue;
                case '+':
                    symbols.add(new Expression(SignType.PLUS, mean));
                    pos++;
                    continue;
                case '-':
                    symbols.add(new Expression(SignType.MINUS, mean));
                    pos++;
                    continue;
                case '*':
                    symbols.add(new Expression(SignType.MULTIPLY, mean));
                    pos++;
                    continue;
                case '/':
                    symbols.add(new Expression(SignType.DIVIDE, mean));
                    pos++;
                    continue;
                default:
                    if (mean >= '0' && mean <= '9') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(mean);
                            pos++;
                            if (pos >= strInput.length()) {
                                break;
                            }
                            mean = strInput.charAt(pos);
                        } while (mean >= '0' && mean <= '9');
                        symbols.add(new Expression(SignType.NUMBER, sb.toString()));
                    } else {
                        if (mean != ' ') {
                            throw new RuntimeException("Недопустимое значение " + mean);
                        }
                        pos++;
                    }
            }
        }
        symbols.add(new Expression(SignType.EOF, ""));
        return symbols;
    }

    public static int expr(ExpressionBuffer expressions) {
        Expression expression = expressions.next();
        if (expression.type == SignType.EOF) {
            return 0;
        } else {
            expressions.back();
            return plusminus(expressions);
        }
    }

    public static int plusminus(ExpressionBuffer expressions) {
        int value = multdiv(expressions);
        while (true) {
            Expression expression = expressions.next();
            switch (expression.type) {
                case PLUS:
                    value += multdiv(expressions);
                    break;
                case MINUS:
                    value -= multdiv(expressions);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                    expressions.back();
                    return value;
                default:
                    throw new RuntimeException("Недопустимое значение");
            }
        }
    }

    public static int multdiv(ExpressionBuffer expressions) {
        int value = factor(expressions);
        while (true) {
            Expression expression = expressions.next();
            switch (expression.type) {
                case MULTIPLY:
                    value *= factor(expressions);
                    break;
                case DIVIDE:
                    value /= factor(expressions);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case PLUS:
                case MINUS:
                    expressions.back();
                    return value;
                default:
                    throw new RuntimeException("Недопустимое значение");
            }
        }
    }

    public static int factor(ExpressionBuffer expressions) {
        Expression expression = expressions.next();
        switch (expression.type) {
            case NUMBER:
                return Integer.parseInt(expression.value);
            case LEFT_BRACKET:
                int value = plusminus(expressions);
                expression = expressions.next();
                if (expression.type != SignType.RIGHT_BRACKET) {
                    throw new RuntimeException("Недопустимое значение");
                }
                return value;
            default:
                throw new RuntimeException("Недопустимое значение");
        }
    }
}
