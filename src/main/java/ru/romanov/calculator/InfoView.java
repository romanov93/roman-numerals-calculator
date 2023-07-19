package ru.romanov.calculator;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class InfoView extends Stage {
    private final VBox vBox = new VBox();
    public InfoView() {}
    public void showInfoWindow() {
        String[] lines = getInfoText();
        for (String string : lines) {
            Text text = new Text(string);
            text.setFont(Font.font(15));
            vBox.getChildren().add(text);
        }

        Scene scene = new Scene(vBox, 300, 450);
        setScene(scene);

        setResizable(false);
        setTitle("Справка");
        show();
    }

    private String[] getInfoText() {
        String[] strings = new String[22];
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


}
