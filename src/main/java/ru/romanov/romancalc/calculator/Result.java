package ru.romanov.romancalc.calculator;

public class Result {

    private final int fullPart;
    private final int fractionPartX1728;

    public Result(int fullPart, int fractionPartX1728) {
        this.fullPart = fullPart;
        this.fractionPartX1728 = fractionPartX1728;
    }

    public int getFullPart() {
        return fullPart;
    }

    public int getFractionPartX1728() {
        return fractionPartX1728;
    }
}
