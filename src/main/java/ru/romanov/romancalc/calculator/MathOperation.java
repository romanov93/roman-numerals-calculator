package ru.romanov.romancalc.calculator;

import ru.romanov.romancalc.utils.AlertsMaker;

import java.math.BigInteger;

public class MathOperation {

    private final long input1;
    private final long input2;
    private final Action action;
    private final AlertsMaker alertsMaker;


    public MathOperation(long input1, long input2, Action action, AlertsMaker alertsMaker) {
        this.input1 = input1;
        this.input2 = input2;
        this.action = action;
        this.alertsMaker = alertsMaker;
    }

    public Result findResult() {
        Result result = calculateResult();
        String round = findRound();
        boolean resultHaveRoundAndPlacedInRomanRange = !round.isEmpty() && result.getFullPart() < 4000000;
        if (resultHaveRoundAndPlacedInRomanRange)
            alertsMaker.showRoundingAlert(round);
        return result;
    }

    private Result calculateResult() {
        long resultMultiplied1728 = findResultMultiplied1728();
        long fullPart =  (resultMultiplied1728 / 1728);
        int fractionPart = (int) (resultMultiplied1728 % 1728);
        return new Result(fullPart, fractionPart);
    }

    private long findResultMultiplied1728() {
        return switch (action) {
            case ADDITION -> addict();
            case SUBTRACTION -> subtract();
            case MULTIPLICATION -> multiply();
            case DIVISION -> divide();
        };
    }

    private long multiply() {
        BigInteger bigInput1 = BigInteger.valueOf(input1);
        BigInteger bigInput2 = BigInteger.valueOf(input2);
        BigInteger bigResult = bigInput1.multiply(bigInput2);
        BigInteger divider = BigInteger.valueOf(1728);
        return bigResult.divide(divider).longValue();
    }

    private long divide() {
        return (input1 * 1728) / input2;
    }

    private long addict() {
        return input1 + input2;
    }

    private long subtract() {
        return input1 - input2;
    }

    String findRound() {
        double roundX1728 = 0;
        switch (action) {
            case DIVISION -> roundX1728 = ((double) (input1 * 1728) / input2) % 1;
            case MULTIPLICATION -> roundX1728 = (new BigInteger(String.valueOf(input1))
                            .multiply(BigInteger.valueOf(input2))
                            .remainder(BigInteger.valueOf(1728)).doubleValue() / 1728);
        }
        if (roundX1728 == 0) return "";
        String round = String.format("%.10f", roundX1728/1728);
        return round;
    }
}
