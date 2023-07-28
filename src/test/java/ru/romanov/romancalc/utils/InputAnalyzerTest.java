package ru.romanov.romancalc.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.romanov.romancalc.utils.InputAnalyzer.*;

class InputAnalyzerTest {

    @ParameterizedTest
    @ValueSource(strings = {"M̅+", "S•••Ɔ»»-", "X̅L̅Iƻ:", "»×"})
    void lastSymbolIsActionTest(String input) {
        assertThat(isLastInputSymbolIsAction(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"M̅+I", "L", "", "MDLXXXI+S•Ɔ»»"})
    void lastSymbolIsNotActionTest(String input) {
        assertThat(isLastInputSymbolIsAction(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"D̅+", "XL:I", "I+I", "Ƨ×X̅XVIII"})
    void textFieldContainsActionTest(String input) {
        assertThat(isTextFieldContainsAction(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"I", "", "M̅M̅M̅D̅C̅C̅C̅X̅XXXIV", "S•••ЄƆƻ℈»»"})
    void textFieldDontContainsActionTest(String input) {
        assertThat(isTextFieldContainsAction(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"V+V̅", "M̅M̅VI:Sƻ»"})
    void bothNumbersAlreadyInputText(String input) {
        assertThat(isBothNumbersAlreadyInput(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"M̅", "S•", "", "XCII+", "•:"})
    void notBothNumbersAlreadyInputText(String input) {
        assertThat(isBothNumbersAlreadyInput(input)).isFalse();
    }

}
