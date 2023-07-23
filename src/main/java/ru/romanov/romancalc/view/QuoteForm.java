package ru.romanov.romancalc.view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.romanov.romancalc.App;

public class QuoteForm extends VBox {

    private final Text quote = new Text();
    private final Text translate = new Text();

    public QuoteForm() {
        setAlignment(Pos.CENTER);
        configureElementsStyle();

        getChildren().add(quote);
        getChildren().add(translate);
    }

    private void configureElementsStyle() {
        quote.setFont(getBeautyFont());
        translate.setFont(Font.font("Arial Nova Light", 15));
    }

    private Font getBeautyFont() {
        return Font.loadFont(App.class.getClassLoader().getResourceAsStream("FrenchScriptMT.ttf"), 28);
    }

    public Text getQuote() {
        return quote;
    }

    public Text getTranslate() {
        return translate;
    }
}
