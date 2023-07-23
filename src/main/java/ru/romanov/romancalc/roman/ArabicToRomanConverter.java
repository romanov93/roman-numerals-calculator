package ru.romanov.romancalc.roman;

import ru.romanov.romancalc.calculator.Result;

import java.util.HashMap;

public class ArabicToRomanConverter {
    public String convert(Result result) {
        if (result.isZero()) return "nulla";
        String romanFullPartOfNumber = fullPartToRoman(result.getFullPart());
        String romanFractionPartOfNumber = fractionPartToRoman(result.getFractionPartMultiplied1728());
        return romanFullPartOfNumber + romanFractionPartOfNumber;
    }

    private String fullPartToRoman(int num) {
        if (num == 0) return "";
        String[] M100 = {"", "M̅", "M̅M̅", "M̅M̅M̅"};
        String[] C100 = {"", "C̅", "C̅C̅", "C̅C̅C̅", "C̅D̅", "D̅", "D̅C̅", "D̅C̅C̅", "D̅C̅C̅C̅", "C̅M̅"};
        String[] X100 = {"", "X̅", "X̅X̅", "X̅X̅X̅", "X̅L̅", "L̅", "L̅X̅", "L̅X̅X̅", "L̅X̅X̅X̅", "X̅C̅"};
        String[] M =    {"", "M", "MM", "MMM", "MV̅", "V̅", "V̅M", "V̅MM", "V̅MMM", "MX̅"};
        String[] C =    {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X =    {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I =    {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M100[num/1000000] + C100[num%1000000/100000] + X100[num%100000/10000]
                + M[num%10000/1000] + C[(num%1000)/100]+ X[(num%100)/10] + I[num%10];
    }

    private String fractionPartToRoman(int fractionPartX1728) {
        if (fractionPartX1728 == 0) return "";
        HashMap<Character, Integer> romanFractionsAndValues = new HashMap<>();
        romanFractionsAndValues.put('S', 864);
        romanFractionsAndValues.put('•', 144);
        romanFractionsAndValues.put('Є', 72);
        romanFractionsAndValues.put('Ɔ', 36);
        romanFractionsAndValues.put('Ƨ', 24);
        romanFractionsAndValues.put('ƻ', 12);
        romanFractionsAndValues.put('℈', 6);
        romanFractionsAndValues.put('»', 1);

        char[] fractionDigits = {'S', '•', 'Є', 'Ɔ', 'Ƨ', 'ƻ', '℈', '»'};
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < fractionDigits.length ; i++) {
            char currentChar = fractionDigits[i];
            int value = romanFractionsAndValues.get(currentChar);
            while (fractionPartX1728 >= value) {
                sb.append(currentChar);
                fractionPartX1728 -= value;
            }
        }
        return sb.toString();
    }



}
