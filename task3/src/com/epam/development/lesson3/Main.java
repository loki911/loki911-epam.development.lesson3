package com.epam.development.lesson3;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Input input = new Input();
        String strInput = input.mainInput();

        List<Expression> symbols = Constructor.expAnalyze(strInput);
        ExpressionBuffer expBuf = new ExpressionBuffer(symbols);
        Output output = new Output();
        output.mainOutput(expBuf);

    }
}