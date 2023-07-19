package ru.romanov.romancalc.view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.romanov.romancalc.App;
import ru.romanov.romancalc.quote.Quote;
import ru.romanov.romancalc.quote.QuoteRandomizer;

import java.io.InputStream;

public class QuoteView extends VBox {

    private final Text quote = new Text();
    private final Text translate = new Text();
    private final QuoteRandomizer randomizer = new QuoteRandomizer();

    public QuoteView() {
        setAlignment(Pos.CENTER);
        configureElementsStyle();

        getChildren().add(quote);
        getChildren().add(translate);
    }

    private void configureElementsStyle() {
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("FrenchScriptMT.ttf");
        quote.setFont(Font.loadFont(inputStream, 28));
        //quote.setFont(Font.loadFont("file:src/main/resources/FrenchScriptMT.ttf", 28));
        translate.setFont(Font.font("Arial Nova Light", 15));
    }

    public void randomizeQuoteText() {
        Quote randomQuote = randomizer.getRandomQuote();
        quote.setText(randomQuote.getLatinText());
        translate.setText(randomQuote.getTranslate());
    }
}
