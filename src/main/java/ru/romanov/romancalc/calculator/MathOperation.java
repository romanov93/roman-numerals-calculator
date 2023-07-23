package ru.romanov.romancalc.calculator;

import ru.romanov.romancalc.utils.AlertsMaker;

public class MathOperation {

    private final long input1;
    private final long input2;
    private final String action;

    public MathOperation(long input1, long input2, String action) {
        this.input1 = input1;
        this.input2 = input2;
        this.action = action;
    }

    public Result findResult() {
        long resultMultiplied1728 = findResultMultiplied1728();

        checkForRounding();

        int fullPart = (int) (resultMultiplied1728 / 1728);
        int fractionPart = (int) (resultMultiplied1728 % 1728);

        return new Result(fullPart, fractionPart);
    }

    long findResultMultiplied1728() {
        if (action.equals("-"))
            return input1 - input2;
        else if (action.equals("+"))
            return input1 + input2;
        else if (action.equals(":"))
            return (input1 * 1728) / input2;
        else
            return (input1 * input2) / 1728;
    }

    void checkForRounding() {
        double round = 0;
        if (action.equals(":"))
            round = ((double) (input1 * 1728) / input2) % 1;
        else if (action.equals("×"))
            round = ((double) (input1 * input2) / 1728) % 1;
        if (round != 0)
            AlertsMaker.showRoundingAlert(round);
    }
}
