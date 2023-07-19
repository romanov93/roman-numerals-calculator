package ru.romanov.romancalc.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ru.romanov.romancalc.App;
import ru.romanov.romancalc.view.calc.CalculatorView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainAppView extends StackPane {

    private final BorderPane borderPane = new BorderPane();
    private final CalculatorView calculatorView = new CalculatorView();
    private final QuoteView quoteView = new QuoteView();

    public MainAppView() throws FileNotFoundException {
        configureBorderPane();
        configureQuoteView();

        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("background.png");
        getChildren().add(new ImageView(new Image(inputStream)));
        //getChildren().add(new ImageView("file:src/main/resources/background.png"));
        getChildren().add(borderPane);
    }

    private void configureBorderPane() {
        borderPane.setTop(calculatorView);
        borderPane.setCenter(quoteView);
    }

    private void configureQuoteView() {
        quoteView.randomizeQuoteText();
        calculatorView.getCalculatorForm().getNextQuote().setOnAction(click -> quoteView.randomizeQuoteText());
    }
}