package ru.romanov.calculator;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;

public class MainAppView extends StackPane {

    private final BorderPane borderPane = new BorderPane();
    private final CalculatorView calculatorPane = new CalculatorView();
    private final QuoteView quotePane = new QuoteView();

    public MainAppView() throws FileNotFoundException {
        configureMainPane();
        configureBorderPane();
        configureQuotePane();
    }

    private void configureMainPane() {
        getChildren().add(new ImageView("file:src/main/resources/background.png"));
        getChildren().add(borderPane);
    }

    private void configureBorderPane() {
        borderPane.setTop(calculatorPane);
        borderPane.setCenter(quotePane);
    }

    private void configureQuotePane() {
        quotePane.randomizeQuoteText();
        calculatorPane.getNextQuote().setOnAction(click -> quotePane.randomizeQuoteText());
    }
}
