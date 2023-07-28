package ru.romanov.romancalc.roman;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.romanov.romancalc.calculator.Result;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Random.class)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ArabicToRomanConverterTest {

    private ArabicToRomanConverter converter;

    @BeforeAll
    void prepareConverter() {
        converter = new ArabicToRomanConverter();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/arabic-to-roman-converter-test-data.csv", delimiter = ';', numLinesToSkip = 1)
    void checkForCorrectResultAfterConvert(long fullPart, int fractionPartX1728, String conversionResult) {
        Result result = new Result(fullPart, fractionPartX1728);
        assertThat(converter.convert(result)).isEqualTo(conversionResult);
    }

}
