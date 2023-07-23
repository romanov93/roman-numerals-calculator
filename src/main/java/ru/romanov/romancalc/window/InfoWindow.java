package ru.romanov.romancalc.window;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InfoWindow extends Stage {
    private final VBox verticalLayout = new VBox();
    public InfoWindow() {
        addTextLinesToVerticalLayout();
        verticalLayout.getChildren().add(githubLink());

        Scene scene = new Scene(verticalLayout, 300, 480);
        setScene(scene);

        setResizable(false);
        setTitle("Справка");
    }

    private void addTextLinesToVerticalLayout() {
        for (String string : getInfoText()) {
            Text text = new Text(string);
            text.setFont(Font.font(15));
            verticalLayout.getChildren().add(text);
        }
    }

    private String[] getInfoText() {
        String[] strings = new String[23];
        strings[0] = "I = 1";
        strings[1] = "V = 5";
        strings[2] = "X = 10";
        strings[3] = "L = 50";
        strings[4] = "C = 100";
        strings[5] = "D = 500";
        strings[6] = "M = 1000";
        strings[7] = "V̅ = 5000";
        strings[8] = "X̅ = 10000";
        strings[9] = "L̅ = 50000";
        strings[10] = "C̅ = 100000";
        strings[11] = "D̅ = 500000";
        strings[12] = "M̅ = 1000000";
        strings[13] = "S = 1/2";
        strings[14] = "• = 1/12";
        strings[15] = "Є = 1/24";
        strings[16] = "Ɔ = 1/48";
        strings[17] = "Ƨ = 1/72";
        strings[18] = "ƻ = 1/144";
        strings[19] = "℈ = 1/288";
        strings[20] = "» = 1/1728";
        strings[21] = "nulla = 0";
        return strings;
    }

    private Hyperlink githubLink() {
        Hyperlink link = new Hyperlink("Перейти на GitHub");
        link.setOnAction(click -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/romanov93/roman-numerals-calculator"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        return link;
    }


}
