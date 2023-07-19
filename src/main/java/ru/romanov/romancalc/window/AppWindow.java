package ru.romanov.romancalc.window;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.romanov.romancalc.view.MainAppView;

import java.io.FileNotFoundException;

public class AppWindow extends Application {

    private final int WIDTH = 400;
    private final int HEIGHT = 500;
    private final String TITLE = "Roman calculator";
    private final MainAppView mainView;

    {
        try {
            mainView = new MainAppView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(new Image("file:src/main/resources/icon.png"));

        Scene scene = new Scene(mainView, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}