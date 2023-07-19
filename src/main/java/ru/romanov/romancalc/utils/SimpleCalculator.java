package ru.romanov.romancalc.utils;

public class SimpleCalculator {

    public static double findResult(double input1, double input2, String action) {
        double result = 0;
        if (action.equals("-"))
            result = input1 - input2;
        else if (action.equals("+"))
            result = input1 + input2;
        else if (action.equals(":"))
            result = input1 / input2;
        else if (action.equals("×"))
            result = input1 * input2;
        return result;
    }
}
