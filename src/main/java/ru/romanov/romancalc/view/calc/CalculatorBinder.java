package ru.romanov.romancalc.view.calc;

import javafx.scene.control.Button;
import ru.romanov.romancalc.roman.ArabicToRomanConverter;
import ru.romanov.romancalc.roman.RomanToArabicConverter;
import ru.romanov.romancalc.roman.RomanValidator;
import ru.romanov.romancalc.utils.SimpleCalculator;
import ru.romanov.romancalc.utils.AlertsMaster;
import ru.romanov.romancalc.window.InfoWindow;

public class CalculatorBinder {

    private final CalculatorForm calculatorForm;
    private final RomanValidator romanValidator = new RomanValidator();
    private final RomanToArabicConverter converterToArabic = new RomanToArabicConverter();
    private final ArabicToRomanConverter converterToRoman = new ArabicToRomanConverter();

    public CalculatorBinder(CalculatorForm calculatorForm) {
        this.calculatorForm = calculatorForm;
    }

    private void cutLastSymbols() {
        String input = calculatorForm.getTextField().getText();
        if (input.isEmpty()) return;
        if (input.contains("=")) {
            calculatorForm.getTextField().setText(input.split("=")[0]);
            return;
        }
        int symbolsShouldBeCut = 1;
        if (input.charAt(input.length() - 1) == '̅') symbolsShouldBeCut++;
        calculatorForm.getTextField().setText(input.substring(0, input.length() - symbolsShouldBeCut));
    }

    private boolean textFieldContainsAction() {
        String[] actions = {"+", "-", ":", "×"};
        for (String action : actions)
            if (calculatorForm.getTextField().getText().contains(action))
                return true;
        return false;
    }

    private String[] getInputNumbers(String currentAction) {
        String text = calculatorForm.getTextField().getText();
        String[] inputNumbers = new String[2];
        if      (currentAction.equals("-"))
            inputNumbers = text.split("-");
        else if (currentAction.equals("+"))
            inputNumbers = text.split("\\+");
        else if (currentAction.equals(":"))
            inputNumbers = text.split(":");
        else if (currentAction.equals("×"))
            inputNumbers = text.split("×");
        return inputNumbers;
    }

    private String getCurrentAction() {
        String text = calculatorForm.getTextField().getText();
        if      (text.contains("-")) return "-";
        else if (text.contains("+")) return "+";
        else if (text.contains(":")) return ":";
        else if (text.contains("×")) return "×";
        else return "";
    }

    private boolean isReadyToFindResult() {
        String currentAction = getCurrentAction();
        if (currentAction.equals("")) {
            AlertsMaster.showHaveNoNumberAlert();
            return false;
        }
        char lastChar = calculatorForm.getTextField().getText().charAt(calculatorForm.getTextField().getText().length() - 1);
        if (currentAction.charAt(0) == lastChar) {
            AlertsMaster.showHaveNoNumberAlert();
            return false;
        }
        return true;
    }

    private boolean isLastSymbolIsAction() {
        char[] actions = {'+', '-', ':', '×'};
        int lastIndex = calculatorForm.getTextField().getText().length() - 1;
        for (char action : actions)
            if (calculatorForm.getTextField().getText().charAt(lastIndex) == action)
                return true;
        return false;
    }

    private void appendAction(String action) {
        String input = calculatorForm.getTextField().getText();
        if (input.contains("="))
            calculatorForm.getTextField().setText(input.split("=")[1]);
        if (input.contains("nulla"))
            calculatorForm.getTextField().setText("");
        boolean romanNumberIsValid = romanValidator.isNumberValid(calculatorForm.getTextField().getText());
        if (!romanNumberIsValid) return;
        calculatorForm.getTextField().appendText(action);
    }

    private void appendDigit(Button button) {
        if (calculatorForm.getTextField().getText().contains("=")) {
            calculatorForm.getTextField().setText(button.getText());
            return;
        }
        if (calculatorForm.getTextField().getText().isEmpty() || isLastSymbolIsAction()) {
            calculatorForm.getTextField().appendText(button.getText());
            return;
        }
        String validationNumber;
        if (textFieldContainsAction())
            validationNumber = getInputNumbers(getCurrentAction())[1] + button.getText();
        else
            validationNumber = calculatorForm.getTextField().getText() + button.getText();

        boolean numberValid = romanValidator.isNumberValid(validationNumber);
        if (!numberValid) return;

        calculatorForm.getTextField().appendText(button.getText());
    }

    private void calculate() {
        String currentAction = getCurrentAction();
        boolean readyToFindResult = isReadyToFindResult();
        if (!readyToFindResult) return;
        String inputRoman1 = getInputNumbers(currentAction)[0];
        String inputRoman2 = getInputNumbers(currentAction)[1];
        boolean romanNumberIsValid = romanValidator.isNumberValid(inputRoman2);
        if (!romanNumberIsValid) return;
        double input1 = converterToArabic.convert(inputRoman1);
        double input2 = converterToArabic.convert(inputRoman2);
        double result = SimpleCalculator.findResult(input1, input2, currentAction);
        boolean resultCanBeConvertedToRoman = romanValidator.isResultCanBeConvertedToRoman(result);
        if (!resultCanBeConvertedToRoman) return;
        String romanResult = converterToRoman.convert(result);
        if (romanResult.isEmpty()) return;
        calculatorForm.getTextField().appendText("=" + romanResult);
    }

    public void addBinding() {
        for (Button button : calculatorForm.getButtonsWithDigits())
            button.setOnAction(click -> appendDigit(button));
        calculatorForm.getClean().setOnAction(click -> calculatorForm.getTextField().setText(""));
        calculatorForm.getCut().setOnAction(click -> cutLastSymbols());
        calculatorForm.getInfo().setOnAction(click -> new InfoWindow().show());
        calculatorForm.getAddition().setOnAction(click -> appendAction("+"));
        calculatorForm.getDivision().setOnAction(click -> appendAction(":"));
        calculatorForm.getSubtraction().setOnAction(click -> appendAction("-"));
        calculatorForm.getMultiplication().setOnAction(click -> appendAction("×"));
        calculatorForm.getAnswer().setOnAction(click -> calculate());
    }
}
