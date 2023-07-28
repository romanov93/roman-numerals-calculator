package ru.romanov.romancalc.roman;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Random.class)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class RomanToArabicConverterTest {

    private RomanToArabicConverter converter;

    @BeforeAll
    void prepareConverter() {
        converter = new RomanToArabicConverter();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/roman-to-arabic-converter-test-data.csv", delimiter = ';', numLinesToSkip = 1)
    void checkForCorrectResultAfterConvert(String romanInput, long conversionResult) {
        assertThat(converter.convert(romanInput)).isEqualTo(conversionResult);
    }
}