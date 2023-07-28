package ru.romanov.romancalc.utils;


public class InputAnalyzer {

    public static boolean isLastInputSymbolIsAction(String input) {
        if (input.isEmpty()) return false;
        char lastInputChar = input.charAt(input.length() - 1);
        return lastInputChar == '+' || lastInputChar == '-' || lastInputChar == ':' || lastInputChar == '×';
    }

    public static boolean isTextFieldContainsAction(String input) {
        return input.contains("+") || input.contains("-") || input.contains(":") || input.contains("×");
    }

    public static boolean isBothNumbersAlreadyInput(String input) {
        if (input.isEmpty()) return false;
        String inputAction = getInputAction(input);
        char lastChar = input.charAt(input.length() - 1);
        if (inputAction.isEmpty() || inputAction.charAt(0) == lastChar) {
            return false;
        }
        return true;
    }

    static String getInputAction(String input) {
        if      (input.contains("-")) return "-";
        else if (input.contains("+")) return "+";
        else if (input.contains(":")) return ":";
        else if (input.contains("×")) return "×";
        else return "";
    }
}
