package ru.romanov.romancalc.roman;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.romanov.romancalc.calculator.Result;
import ru.romanov.romancalc.utils.AlertsMaker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.Random.class)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class RomanValidatorTest {

    private RomanValidator romanValidator;
    private AlertsMaker alertsMaker;

    @BeforeAll
    void prepareValidator() {
        this.alertsMaker = Mockito.mock(AlertsMaker.class);
        romanValidator = new RomanValidator(alertsMaker);
    }

    @ParameterizedTest
    @ValueSource(strings = {"L", "CCC", "Ƨ", "S•ЄƆƧ℈»»»»»", "M̅C̅X̅X̅X̅MIVS••ƻ"})
    void validateCorrectRomanNumber(String input) {
        assertTrue(romanValidator.isNumberValid(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"SII", "IM", "CVVV", "S•ЄƆƧƻ℈»»»»»", "MDCCCL̅VI"})
    void validateNotCorrectRomanNumber(String input) {
        assertFalse(romanValidator.isNumberValid(input));
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 5, 14, 88, 228, 404, 666, 1024, 1000000, 3999999})
    void resultInRomanRange(long fullPartOfResult) {
        Result result = new Result(fullPartOfResult, 0);
        assertTrue(romanValidator.isResultCanBeConvertedToRoman(result));
    }

    @ParameterizedTest
    @ValueSource(longs = {-3999999, -1, 4000000, 39999999, 10101010})
    void resultNotInRomanRange(long fullPartOfResult) {
        Result result = new Result(fullPartOfResult, 0);
        assertFalse(romanValidator.isResultCanBeConvertedToRoman(result));
    }
}
