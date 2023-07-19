package ru.romanov.romancalc.roman;

import ru.romanov.romancalc.utils.AlertsMaster;

public class RomanValidator {

    private final String pattern =
            "^(M̅){0,3}((C̅M̅)|(C̅D̅)|(D̅)?(C̅){0,3})((X̅C̅)|(X̅L̅)|(L̅)?(X̅){0,3})((MX̅)|(MV̅)|(V̅)?M{0,3})" +
            "(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})S?•{0,5}Є?Ɔ?[Ƨƻ]?℈?»{0,5}$";

    public boolean isNumberValid(String input) {
        if (input.contains("=")) {
            AlertsMaster.showAlreadyHaveResultAlert();
            return false;
        }
        if (input.equals("")) {
            AlertsMaster.showEmptyFieldAlert();
            return false;
        }
        if (input.contains("+") || input.contains("-") || input.contains(":") || input.contains("×")) {
            AlertsMaster.showAlreadySelectedActionAlert();
            return false;
        }
        if (!input.matches(pattern)) {
            AlertsMaster.showWrongNumberFormat(input);
            return false;
        }
        return true;
    }

    public boolean isResultCanBeConvertedToRoman (double result) {
        if (result == 0) {
            return true;
        }
        if (result < 0) {
            AlertsMaster.showNegativeAlert(result);
            return false;
        }
        if ((result * 1728) < 1) {
            AlertsMaster.showSmallSizeAlert(result);
            return false;
        }
        if (result > 3999999) {
            AlertsMaster.showBigSizeAlert(result);
            return false;
        }
        return true;
    }
}
