package ru.romanov.romancalc.utils;

import javafx.scene.control.Alert;

public class AlertsMaster {

    public static void showNegativeAlert(double result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("В Риме не было отрицательных чисел");
        alert.setContentText("Результат (" + result + ") невозможно записать римскими цифрами.");
        alert.show();
    }

    public static void showBigSizeAlert(double result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("Нет символов для записи таких больших чисел");
        alert.setContentText("Результат (" + result + ") невозможно записать римскими цифрами." + "\n"
        + "Максимальное число - 3999999");
        alert.show();
    }

    public static void showSmallSizeAlert(double result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("Нет символов для записи таких маленьких чисел");
        alert.setContentText("Результат (" + result + ") невозможно записать римскими цифрами." + "\n"
                + "Минимальное число: 1/1728");
        alert.show();
    }

    public static void showWrongNumberFormat(String romanNumber) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setContentText("Некорректная запись числа: " + romanNumber);
        alert.show();
    }
    
    public static void showAlreadySelectedActionAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setContentText("Уже выбрано математическое действие");
        alert.show();
    }

    public static void showEmptyFieldAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setContentText("Не введено число перед действием.");
        alert.show();
    }

    public static void showAlreadyHaveResultAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setContentText("Результат уже найден.");
        alert.show();
    }

    public static void showHaveNoNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Неполный ввод");
        alert.setContentText("Не введено число или действие");
        alert.show();
    }

    public static void showAlertAboutRounding(double remainder) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("Ответ округлен менее чем на 0,001");
        alert.setContentText("Округление на: " + "\n" + remainder + " / 1728");
        alert.show();
    }

}
