package ru.romanov.romancalc.view.calc;

import javafx.scene.layout.Pane;

public class CalculatorView extends Pane {

    private final CalculatorForm calculatorForm = new CalculatorForm();
    private final CalculatorBinder binder = new CalculatorBinder(calculatorForm);

    public CalculatorView() {
        binder.addBinding();
        getChildren().add(calculatorForm);
    }

    public CalculatorForm getCalculatorForm() {
        return calculatorForm;
    }
}
