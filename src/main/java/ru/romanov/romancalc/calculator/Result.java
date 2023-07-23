package ru.romanov.romancalc.calculator;

public class Result {

    private final int fullPart;
    private final int fractionPartMultiplied1728;

    public Result(int fullPart, int fractionPartX1728) {
        this.fullPart = fullPart;
        this.fractionPartMultiplied1728 = fractionPartX1728;
    }

    public int getFullPart() {
        return fullPart;
    }

    public int getFractionPartMultiplied1728() {
        return fractionPartMultiplied1728;
    }

    public boolean isZero() {
        return (getFullPart() == 0 && getFractionPartMultiplied1728() == 0);
    }

    @Override
    public String toString() {
        return "Result{" +
                "fullPart= " + fullPart +
                ", fractionPartMultiplied1728= " + fractionPartMultiplied1728 +
                '}';
    }
}
