module ru.romanov.calculator {
    requires javafx.controls;
    requires javafx.media;
    requires java.desktop;


    opens ru.romanov.romancalc;
    exports ru.romanov.romancalc;
    exports ru.romanov.romancalc.roman;
    opens ru.romanov.romancalc.roman;
    exports ru.romanov.romancalc.utils;
    opens ru.romanov.romancalc.utils;
    exports ru.romanov.romancalc.quote;
    opens ru.romanov.romancalc.quote;
    exports ru.romanov.romancalc.window;
    opens ru.romanov.romancalc.window;
    exports ru.romanov.romancalc.calculator;
    opens ru.romanov.romancalc.calculator;
    exports ru.romanov.romancalc.view;
    opens ru.romanov.romancalc.view;
}