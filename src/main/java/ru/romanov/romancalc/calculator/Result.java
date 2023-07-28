package ru.romanov.romancalc.calculator;

public class Result {

    private final long fullPart;
    private final int fractionPartMultiplied1728;

    public Result(long fullPart, int fractionPartX1728) {
        this.fullPart = fullPart;
        this.fractionPartMultiplied1728 = fractionPartX1728;
    }

    public long getFullPart() {
        return fullPart;
    }

    public int getFractionPartMultiplied1728() {
        return fractionPartMultiplied1728;
    }

    public boolean isZero() {
        return (getFullPart() == 0 && getFractionPartMultiplied1728() == 0);
    }

}
