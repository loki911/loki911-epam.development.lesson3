package com.epam.development.lesson3;

public class Expression {
    SignType type;
    String value;

    public Expression(SignType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Expression(SignType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return "Expression{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
