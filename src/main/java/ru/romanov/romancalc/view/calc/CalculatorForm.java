package ru.romanov.romancalc.view.calc;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculatorForm extends Pane {

    private final TextField textField = new TextField();
    private final Button I = new Button("I");
    private final Button V = new Button("V");
    private final Button X = new Button("X");
    private final Button L = new Button("L");
    private final Button C = new Button("C");
    private final Button D = new Button("D");
    private final Button M = new Button("M");
    private final Button V100 = new Button("V̅");
    private final Button X100 = new Button("X̅");
    private final Button L100 = new Button("L̅");
    private final Button C100 = new Button("C̅");
    private final Button D100 = new Button("D̅");
    private final Button M100 = new Button("M̅");
    private final Button div2 = new Button("S");
    private final Button div12 = new Button("•");
    private final Button div24 = new Button("Є");
    private final Button div48 = new Button("Ɔ");
    private final Button div72 = new Button("Ƨ");
    private final Button div144 = new Button("ƻ");
    private final Button div288 = new Button("℈");
    private final Button div1728 = new Button("»");
    private final Button clean = new Button("C");
    private final Button cut = new Button("←");
    private final Button addition = new Button("+");
    private final Button subtraction = new Button("-");
    private final Button multiplication = new Button("×");
    private final Button division = new Button("÷");
    private final Button answer = new Button("=");
    private final Button info = new Button("?");
    private final Button nextQuote = new Button("↓");

    public CalculatorForm() {
        addElementsToPane();
        configureElementsSize();
        configureElementsCoordinates();
        addTextUnderButtons();
        configureElementsStyle();
    }

    private void addElementsToPane() {
        getChildren().add(textField);
        for (Button button : getButtonsWithDigits())
            getChildren().add(button);
        for (Button button : getSpecialButtons())
            getChildren().add(button);
    }

    private void configureElementsSize() {
        textField.setPrefSize(380, 50);
        for (Button button : getButtonsWithDigits())
            button.setPrefSize(45, 45);
        for (Button button : getSpecialButtons())
            button.setPrefSize(45, 45);
    }

    private void configureElementsCoordinates() {
        textField.setLayoutX(10);
        textField.setLayoutY(7);
        List<Button> allButtons = new ArrayList<>();
        Collections.addAll(allButtons, getButtonsWithDigits());
        Collections.addAll(allButtons, getSpecialButtons());
        arrangeButtons(allButtons);
    }

    private void arrangeButtons(List<Button> buttons) {
        int x = 66;
        int y = 77;
        final int SHIFT = 56;
        for (int i = 0 ; i < buttons.size() ; i++) {
            buttons.get(i).setLayoutX(x);
            buttons.get(i).setLayoutY(y);
            x += SHIFT;
            if ((i + 1) % 5 == 0) {
                x = 66;
                y += SHIFT;
            }
        }
    }

    private void addTextUnderButtons() {
        int x = 68;
        int y = 131;
        final int SHIFT = 56;
        String[] info = {"1", "5", "10", "50", "100", "500", "1000", "5000", "10000", "50000", "100000", "500000",
                "1000000", "1/2", "1/12", "1/24", "1/48", "1/72", "1/144", "1/288", "1/1728"};
        Font font = Font.font(10);
        Color color = Color.color(0, 0, 0, 0.5);
        for (int i = 0 ; i < info.length ; i++) {
            Text text = new Text();
            text.setText(info[i]);
            text.setFont(font);
            text.setFill(color);
            getChildren().add(text);
            text.setLayoutX(x);
            text.setLayoutY(y);
            x += SHIFT;
            if ((i + 1) % 5 == 0) {
                x = 68;
                y += SHIFT;
            }
        }
    }

    private void configureElementsStyle() {
        textField.setEditable(false);
        textField.setFont(Font.font(20));

        Font font = Font.font(15);
        for (Button button : getButtonsWithDigits())
            button.setFont(font);

        Font boldFont = Font.font("Arial Black", FontWeight.BOLD, 15);
        for (Button button : getSpecialButtons())
            button.setFont(boldFont);

        clean.setTextFill(Color.DARKRED);
    }

    public Button[] getButtonsWithDigits() {
        return new Button[] {
                I, V, X, L, C, D, M, V100, X100, L100, C100, D100, M100,
                div2, div12, div24, div48, div72, div144, div288, div1728};
    }

    public Button[] getSpecialButtons() {
        return new Button[] {cut, clean, info, answer, addition, subtraction, multiplication, division, nextQuote};
    }

    public Button getNextQuote() {
        return nextQuote;
    }
    public TextField getTextField() {
        return textField;
    }
    public Button getClean() {
        return clean;
    }
    public Button getCut() {
        return cut;
    }
    public Button getAddition() {
        return addition;
    }
    public Button getSubtraction() {
        return subtraction;
    }
    public Button getMultiplication() {
        return multiplication;
    }
    public Button getDivision() {
        return division;
    }
    public Button getAnswer() {
        return answer;
    }
    public Button getInfo() {
        return info;
    }
}
