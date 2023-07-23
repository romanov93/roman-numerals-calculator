package ru.romanov.romancalc.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ru.romanov.romancalc.App;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainAppView extends StackPane {

    private final BorderPane borderPane = new BorderPane();
    private final CalculatorForm calculatorForm = new CalculatorForm();
    private final QuoteForm quoteForm = new QuoteForm();
    private final Binder binder = new Binder(calculatorForm, quoteForm);

    public MainAppView() throws FileNotFoundException {
        borderPane.setTop(calculatorForm);
        borderPane.setCenter(quoteForm);
        binder.addBinding();

        getChildren().add(getAppBackgroundPicture());
        getChildren().add(borderPane);
    }

    private ImageView getAppBackgroundPicture() {
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("background.png");
        return new ImageView(new Image(inputStream));
    }

}