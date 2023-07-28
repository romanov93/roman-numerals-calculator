package ru.romanov.romancalc.utils;

import javafx.scene.control.Alert;

public class AlertsMaker {

    public void showNegativeAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("В Риме не было отрицательных чисел");
        alert.setContentText("Результат невозможно записать римскими цифрами.");

        alert.show();
    }

    public void showBigSizeAlert(double result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("Нет символов для записи таких больших чисел");
        alert.setContentText("Результат (" + result + "...) невозможно записать римскими цифрами." + "\n"
        + "Максимальное число - 3999999");
        alert.show();
    }

    public void showWrongNumberFormatAlert(String romanNumber) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Некорректная запись числа: " + romanNumber);
        alert.setContentText("Нарушены правила записи римских чисел");
        alert.show();
    }
    
    public void showAlreadySelectedActionAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        
        alert.setContentText("Уже выбрано математическое действие");
        alert.show();
    }

    public void showEmptyFieldAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setContentText("Не введено число перед действием.");
        alert.show();
    }

    public void showAlreadyHaveResultAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setContentText("Результат уже найден.");
        alert.show();
    }

    public void showNoInputNumberAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Неполный ввод");
        alert.setContentText("Не введено число или действие");
        alert.show();
    }

    public void showRoundingAlert(String round) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("Ответ был округлен в меньшую сторону.");
        alert.setContentText("Произошло округление на: " + round + "...");
        alert.show();
    }

}
