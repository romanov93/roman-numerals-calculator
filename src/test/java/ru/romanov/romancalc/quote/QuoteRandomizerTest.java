package ru.romanov.romancalc.quote;

import org.junit.jupiter.api.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class QuoteRandomizerTest {

    private QuoteRandomizer quoteRandomizer;

    @BeforeAll
    void prepareRandomizer() {
        quoteRandomizer = new QuoteRandomizer();
    }

    @RepeatedTest(20)
    void randomQuoteShouldHaveTextAndTranslate() {
        Quote quote = quoteRandomizer.getRandomQuote();
        Assertions.assertFalse(quote.getLatinText().isEmpty());
        Assertions.assertFalse(quote.getTranslate().isEmpty());
    }

}
