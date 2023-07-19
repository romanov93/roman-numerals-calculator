package ru.romanov.romancalc.roman;

import java.util.HashMap;

public class RomanToArabicConverter {

    public double convert(String inputNumber) {
        String fractionPart = findFractionPart(inputNumber);
        String fullPart = inputNumber.replace(fractionPart, "");
        return romanToLong(fullPart) + romanFractionToDouble(fractionPart);
    }

    private long romanToLong(String input) {
        if (input.length() == 0) return 0;
        String convertable = input
                .replace("V̅", "v").replace("X̅", "x").replace("L̅", "l")
                .replace("C̅", "c").replace("D̅", "d").replace("M̅", "m");
        HashMap<Character, Integer> romanDigits = new HashMap<>();
        romanDigits.put('I', 1);
        romanDigits.put('V', 5);
        romanDigits.put('X', 10);
        romanDigits.put('L', 50);
        romanDigits.put('C', 100);
        romanDigits.put('D', 500);
        romanDigits.put('M', 1000);
        romanDigits.put('v', 5000);
        romanDigits.put('x', 10000);
        romanDigits.put('l', 50000);
        romanDigits.put('c', 100000);
        romanDigits.put('d', 500000);
        romanDigits.put('m', 1000000);

        int result = 0;
        for(int i = 0; i < convertable.length() - 1; i++){
            char currentDigit = convertable.charAt(i);
            char nextDigit = convertable.charAt(i+1);
            if(romanDigits.get(currentDigit) < romanDigits.get(nextDigit))
                result -= romanDigits.get(currentDigit);
            else
                result += romanDigits.get(currentDigit);
        }
        return result + romanDigits.get(convertable.charAt(convertable.length()-1));
    }

    private double romanFractionToDouble(String input) {
        if (input.length() == 0) return 0;
        HashMap<Character, Integer> romanFractions = new HashMap<>();
        romanFractions.put('S', 864);
        romanFractions.put('•', 144);
        romanFractions.put('Є', 72);
        romanFractions.put('Ɔ', 36);
        romanFractions.put('Ƨ', 24);
        romanFractions.put('ƻ', 12);
        romanFractions.put('℈', 6);
        romanFractions.put('»', 1);
        int divider = 0;
        for (int i = 0 ; i < input.length() ; i++) {
            divider += romanFractions.get(input.charAt(i));
        }
        return divider / (double) 1728;
    }

    private String findFractionPart(String input) {
        return input.replace("I", "").replace("V", "").replace("X", "")
                .replace("L", "").replace("C", "").replace("D", "")
                .replace("M", "").replace("̅", "");
    }
}