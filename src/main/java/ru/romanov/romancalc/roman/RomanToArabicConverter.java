package ru.romanov.romancalc.roman;

import java.util.HashMap;

public class RomanToArabicConverter {

    public long convert(String inputNumber) {
        String fractionPart = getFractionPart(inputNumber);
        String fullPart = inputNumber.replace(fractionPart, "");
        return romanToLong(fullPart) * 1728L + romanFractionToDouble(fractionPart);
    }

    private int romanToLong(String input) {
        if (input.length() == 0) return 0;
        String convertable = getConvertableRoman(input);
        HashMap<Character, Integer> romanDigitsAndValues = new HashMap<>();
        romanDigitsAndValues.put('I', 1);
        romanDigitsAndValues.put('V', 5);
        romanDigitsAndValues.put('X', 10);
        romanDigitsAndValues.put('L', 50);
        romanDigitsAndValues.put('C', 100);
        romanDigitsAndValues.put('D', 500);
        romanDigitsAndValues.put('M', 1000);
        romanDigitsAndValues.put('v', 5000);
        romanDigitsAndValues.put('x', 10000);
        romanDigitsAndValues.put('l', 50000);
        romanDigitsAndValues.put('c', 100000);
        romanDigitsAndValues.put('d', 500000);
        romanDigitsAndValues.put('m', 1000000);

        int result = 0;
        for (int i = 0; i < convertable.length() - 1; i++) {
            char currentDigit = convertable.charAt(i);
            char nextDigit = convertable.charAt(i+1);
            if(romanDigitsAndValues.get(currentDigit) < romanDigitsAndValues.get(nextDigit))
                result -= romanDigitsAndValues.get(currentDigit);
            else
                result += romanDigitsAndValues.get(currentDigit);
        }
        return result + romanDigitsAndValues.get(convertable.charAt(convertable.length() - 1));
    }

    private int romanFractionToDouble(String input) {
        if (input.length() == 0) return 0;
        HashMap<Character, Integer> romanFractionsAndValues = new HashMap<>();
        romanFractionsAndValues.put('S', 864);
        romanFractionsAndValues.put('•', 144);
        romanFractionsAndValues.put('Є', 72);
        romanFractionsAndValues.put('Ɔ', 36);
        romanFractionsAndValues.put('Ƨ', 24);
        romanFractionsAndValues.put('ƻ', 12);
        romanFractionsAndValues.put('℈', 6);
        romanFractionsAndValues.put('»', 1);
        int divider = 0;
        for (int i = 0 ; i < input.length() ; i++) {
            divider += romanFractionsAndValues.get(input.charAt(i));
        }
        return divider;
    }

    private String getFractionPart(String input) {
        return input
                .replace("I", "").replace("V", "").replace("X", "")
                .replace("L", "").replace("C", "").replace("D", "")
                .replace("M", "").replace("̅", "");
    }

    public String getConvertableRoman(String input) {
        return input
                .replace("V̅", "v").replace("X̅", "x")
                .replace("L̅", "l").replace("C̅", "c")
                .replace("D̅", "d").replace("M̅", "m");
    }
}