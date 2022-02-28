package com.epam.development.lesson3;

import java.util.List;

public class ExpressionBuffer {
    private int pos;

    public List<Expression> signs;

    public ExpressionBuffer(List<Expression> signs) {
        this.signs = signs;
    }

    public Expression next() {
        return signs.get(pos++);
    }

    public void back() {
        pos--;
    }

    public int getPos() {
        return pos;
    }
}
