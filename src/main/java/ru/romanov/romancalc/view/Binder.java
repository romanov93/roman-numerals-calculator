package ru.romanov.romancalc.view;

import javafx.scene.control.Button;
import ru.romanov.romancalc.calculator.Action;
import ru.romanov.romancalc.calculator.Result;
import ru.romanov.romancalc.quote.Quote;
import ru.romanov.romancalc.quote.QuoteRandomizer;
import ru.romanov.romancalc.roman.ArabicToRomanConverter;
import ru.romanov.romancalc.roman.RomanToArabicConverter;
import ru.romanov.romancalc.roman.RomanValidator;
import ru.romanov.romancalc.calculator.MathOperation;
import ru.romanov.romancalc.utils.AlertsMaker;
import ru.romanov.romancalc.utils.InputAnalyzer;
import ru.romanov.romancalc.window.InfoWindow;

public class Binder {

    private final CalculatorForm calculatorForm;
    private final QuoteForm quoteForm;
    private final AlertsMaker alertsMaker = new AlertsMaker();
    private final RomanValidator romanValidator = new RomanValidator(alertsMaker);
    private final RomanToArabicConverter converterToArabic = new RomanToArabicConverter();
    private final ArabicToRomanConverter converterToRoman = new ArabicToRomanConverter();
    private final QuoteRandomizer quoteRandomizer = new QuoteRandomizer();


    public Binder(CalculatorForm calculatorForm, QuoteForm quoteForm) {
        this.calculatorForm = calculatorForm;
        this.quoteForm = quoteForm;
    }

    private void cutLastDigit() {
        String input = getFullInput();
        if (input.isEmpty()) return;
        if (input.contains("=")) {
            calculatorForm.getTextField().setText(input.split("=")[0]);
            return;
        }
        if (input.charAt(input.length() - 1) == '̅')
            calculatorForm.getTextField().setText(input.substring(0, input.length() - 2));
        else
            calculatorForm.getTextField().setText(input.substring(0, input.length() - 1));
    }

    private void appendAction(String action) {
        String input = getFullInput();
        if (input.isEmpty()) {
            alertsMaker.showEmptyFieldAlert();
            return;
        }
        if (input.contains("nulla")) {
            calculatorForm.getTextField().setText("");
            return;
        }
        if (input.contains("=")) {
            calculatorForm.getTextField().setText(input.split("=")[1]);
            input = getFullInput();
        }
        boolean actionAlreadyInput = InputAnalyzer.isTextFieldContainsAction(input);
        if (actionAlreadyInput) {
            alertsMaker.showAlreadySelectedActionAlert();
            return;
        }
        calculatorForm.getTextField().appendText(action);
    }

    private void appendDigit(Button button) {
        String input = getFullInput();
        if (input.contains("=")) {
            calculatorForm.getTextField().setText(button.getText());
            return;
        }
        boolean secondNumberNotInput = InputAnalyzer.isLastInputSymbolIsAction(input);
        if (input.isEmpty() || secondNumberNotInput) {
            calculatorForm.getTextField().appendText(button.getText());
            return;
        }
        String lastNumberWithNewDigit = getLastInputNumberWithNewDigit(button.getText());
        boolean numberValid = romanValidator.isNumberValid(lastNumberWithNewDigit);
        if (!numberValid) {
            alertsMaker.showWrongNumberFormatAlert(lastNumberWithNewDigit);
            return;
        }
        calculatorForm.getTextField().appendText(button.getText());
    }
    
    private void calculate() {
        if (getFullInput().contains("=")) {
            alertsMaker.showAlreadyHaveResultAlert();
            return;
        }
        boolean bothNumbersAlreadyInput = InputAnalyzer.isBothNumbersAlreadyInput(getFullInput());
        if (!bothNumbersAlreadyInput) {
            alertsMaker.showNoInputNumberAlert();
            return;
        }
        long input1 = converterToArabic.convert(getInputNumbers()[0]);
        long input2 = converterToArabic.convert(getInputNumbers()[1]);
        Result result = new MathOperation(input1, input2, getInputAction(), alertsMaker).findResult();
        boolean resultCanBeConvertedToRoman = romanValidator.isResultCanBeConvertedToRoman(result);
        if (!resultCanBeConvertedToRoman) return;
        String romanResult = converterToRoman.convert(result);
        calculatorForm.getTextField().appendText("=" + romanResult);
    }

    void randomizeQuoteText() {
        Quote randomQuote = quoteRandomizer.getRandomQuote();
        quoteForm.getQuote().setText(randomQuote.getLatinText());
        quoteForm.getTranslate().setText(randomQuote.getTranslate());
    }

    void showNextQuote() {
        Quote nextQuote = quoteRandomizer.getNextQuote();
        quoteForm.getQuote().setText(nextQuote.getLatinText());
        quoteForm.getTranslate().setText(nextQuote.getTranslate());
    }

    public void addBinding() {
        randomizeQuoteText();
        calculatorForm.getNextQuote().setOnAction(click -> showNextQuote());
        for (Button button : calculatorForm.getButtonsWithDigits())
            button.setOnAction(click -> appendDigit(button));
        calculatorForm.getClean().setOnAction(click -> calculatorForm.getTextField().setText(""));
        calculatorForm.getCut().setOnAction(click -> cutLastDigit());
        calculatorForm.getInfo().setOnAction(click -> new InfoWindow().show());
        calculatorForm.getAddition().setOnAction(click -> appendAction("+"));
        calculatorForm.getDivision().setOnAction(click -> appendAction(":"));
        calculatorForm.getSubtraction().setOnAction(click -> appendAction("-"));
        calculatorForm.getMultiplication().setOnAction(click -> appendAction("×"));
        calculatorForm.getAnswer().setOnAction(click -> calculate());
    }

    String[] getInputNumbers() {
        String input = getFullInput();
        String[] inputNumbers = new String[2];
        if      (input.contains("-"))
            inputNumbers = input.split("-");
        else if (input.contains("+"))
            inputNumbers = input.split("\\+");
        else if (input.contains(":"))
            inputNumbers = input.split(":");
        else if (input.contains("×"))
            inputNumbers = input.split("×");
        return inputNumbers;
    }

    Action getInputAction() {
        String input = getFullInput();
        if      (input.contains("-")) return Action.SUBTRACTION;
        else if (input.contains("+")) return Action.ADDITION;
        else if (input.contains(":")) return Action.DIVISION;
        else if (input.contains("×")) return Action.MULTIPLICATION;
        else return null;
    }

    String getFullInput() {
        return calculatorForm.getTextField().getText();
    }

    String getLastInputNumberWithNewDigit(String newDigit) {
        boolean textFieldContainsAction = InputAnalyzer.isTextFieldContainsAction(getFullInput());
        if (textFieldContainsAction)
            return getInputNumbers()[1] + newDigit;
        else
            return getFullInput() + newDigit;
    }


}
