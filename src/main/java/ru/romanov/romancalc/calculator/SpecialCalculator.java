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
        if (action.equals("-") || action.equals("+")) {
            fractionPart = resultX1728 % 1728;
            fullPart = (resultX1728 - fractionPart) / 1728;
        } else if (action.equals("×")) {
            if (resultX1728 < 2985984) {
                fractionPart = resultX1728 / 1728;
            } else {
                fractionPart = resultX1728 % 2985984;
                fullPart = (resultX1728 - fractionPart) / 2985984;
            }
        } else if (action.equals(":")){
            fractionPart = resultX1728 % 1728;
            fullPart = input1 / input2;
        }
        while (fractionPart > 1727) {
            fractionPart -= 1728;
            fullPart++;
        }
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
            return input1 * input2;
    }


}
