package ru.romanov.romancalc.calculator;

public class SpecialCalculator {

    private final long input1;
    private final long input2;
    private final String action;

    public SpecialCalculator(long input1, long input2, String action) {
        this.input1 = input1;
        this.input2 = input2;
        this.action = action;
    }

    public Result findResult() {
        long resultX1728 = findResultX1728();
        long fullPart = 0;
        long fractionPart = 0;

        fractionPart = resultX1728 % 1728;
        if (action.equals(":"))
            fullPart = input1 / input2;
        else
            fullPart = (resultX1728 - fractionPart) / 1728;

        return new Result((int)fullPart, (int)fractionPart);
    }

    private long findResultX1728() {
        if (action.equals("-"))
            return input1 - input2;
        else if (action.equals("+"))
            return input1 + input2;
        else if (action.equals(":"))
            return (input1 * 1728) / input2;
        else
            return (input1 * input2) / 1728;
    }
}
