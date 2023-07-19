module ru.romanov.calculator {
    requires javafx.controls;
    requires javafx.media;


    opens ru.romanov.romancalc;
    exports ru.romanov.romancalc;
    exports ru.romanov.romancalc.view;
    opens ru.romanov.romancalc.view;
    exports ru.romanov.romancalc.roman;
    opens ru.romanov.romancalc.roman;
    exports ru.romanov.romancalc.utils;
    opens ru.romanov.romancalc.utils;
    exports ru.romanov.romancalc.quote;
    opens ru.romanov.romancalc.quote;
    exports ru.romanov.romancalc.view.calc;
    opens ru.romanov.romancalc.view.calc;
    exports ru.romanov.romancalc.window;
    opens ru.romanov.romancalc.window;
    exports ru.romanov.romancalc.calculator;
    opens ru.romanov.romancalc.calculator;
}