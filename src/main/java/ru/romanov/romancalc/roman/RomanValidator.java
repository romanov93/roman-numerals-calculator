package ru.romanov.romancalc.roman;

import ru.romanov.romancalc.calculator.Result;
import ru.romanov.romancalc.utils.AlertsMaker;

public class RomanValidator {

    private final AlertsMaker alertsMaker;

    private final String pattern =
            "^(M̅){0,3}((C̅M̅)|(C̅D̅)|(D̅)?(C̅){0,3})((X̅C̅)|(X̅L̅)|(L̅)?(X̅){0,3})((MX̅)|(MV̅)|(V̅)?M{0,3})" +
            "(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})S?•{0,5}Є?Ɔ?[Ƨƻ]?℈?»{0,5}$";

    public RomanValidator(AlertsMaker alertsMaker) {
        this.alertsMaker = alertsMaker;
    }

    public boolean isNumberValid(String inputRomanNumber) {
        return inputRomanNumber.matches(pattern);
    }

    public boolean isResultCanBeConvertedToRoman (Result result) {
        if (result.getFullPart() < 0 || result.getFractionPartMultiplied1728() < 0) {
            alertsMaker.showNegativeAlert();
            return false;
        }
        if (result.getFullPart() > 3999999) {
            alertsMaker.showBigSizeAlert(result.getFullPart());
            return false;
        }
        return true;
    }
}
