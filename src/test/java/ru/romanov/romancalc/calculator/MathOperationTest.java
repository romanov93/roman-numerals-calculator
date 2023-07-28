package ru.romanov.romancalc.calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import ru.romanov.romancalc.utils.AlertsMaker;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.Random.class)
public class MathOperationTest {
    private static final long maxInput = 6911999999L;
    private final static long mixInput = 1;
    private AlertsMaker alertsMaker;

    @BeforeAll
    void prepareAlertsMaker() {
        this.alertsMaker = Mockito.mock(AlertsMaker.class);
    }

    @Nested
    @DisplayName("MAX value input in both input fields")
    class ResultWithMaximalValueInputTest {
        @Test
        void checkAddition() {
            Result result = new MathOperation(maxInput, maxInput, Action.ADDITION, alertsMaker).findResult();

            Assertions.assertEquals(7999999, result.getFullPart());
            Assertions.assertEquals(1726, result.getFractionPartMultiplied1728());
        }

        @Test
        void checkSubtraction() {
            Result result = new MathOperation(maxInput, maxInput, Action.SUBTRACTION, alertsMaker).findResult();

            Assertions.assertEquals(0, result.getFullPart());
            Assertions.assertEquals(0, result.getFractionPartMultiplied1728());
        }

        @Test
        void checkDivision() {
            Result result = new MathOperation(maxInput, maxInput, Action.DIVISION, alertsMaker).findResult();

            Assertions.assertEquals(1, result.getFullPart());
            Assertions.assertEquals(0, result.getFractionPartMultiplied1728());
        }

        @Test
        void checkMultiplication() {
            Result result = new MathOperation(maxInput, maxInput, Action.MULTIPLICATION, alertsMaker).findResult();

            Assertions.assertEquals(15999999995370L, result.getFullPart());
            Assertions.assertEquals(640, result.getFractionPartMultiplied1728());
        }
    }

    @Nested
    @DisplayName("MIN value input in both input fields")
    class ResultWithMinimalValueInputTest {
        @Test
        void checkAddition() {
            Result result = new MathOperation(mixInput, mixInput, Action.ADDITION, alertsMaker).findResult();

            Assertions.assertEquals(0, result.getFullPart());
            Assertions.assertEquals(2, result.getFractionPartMultiplied1728());
        }

        @Test
        void checkSubtraction() {
            Result result = new MathOperation(mixInput, mixInput, Action.SUBTRACTION, alertsMaker).findResult();

            Assertions.assertEquals(0, result.getFullPart());
            Assertions.assertEquals(0, result.getFractionPartMultiplied1728());
        }

        @Test
        void checkDivision() {
            Result result = new MathOperation(mixInput, mixInput, Action.DIVISION, alertsMaker).findResult();

            Assertions.assertEquals(1, result.getFullPart());
            Assertions.assertEquals(0, result.getFractionPartMultiplied1728());
        }

        @Test
        void checkMultiplication() {
            Result result = new MathOperation(mixInput, mixInput, Action.MULTIPLICATION, alertsMaker).findResult();

            Assertions.assertEquals(0, result.getFullPart());
            Assertions.assertEquals(0, result.getFractionPartMultiplied1728());
        }
    }

    @Nested
    @DisplayName("RANDOM value in both input fields")
    class FindResultWithRandomInputTest {
        private long randomInput1;
        private long randomInput2;


        @BeforeEach
        void prepareRandomInputs() {
            randomInput1 = ThreadLocalRandom.current().nextLong(1, 6911998272L);
            randomInput2 = ThreadLocalRandom.current().nextLong(1, 6911998272L);
        }

        @RepeatedTest(10)
        void checkAddition() {
            Result result = new MathOperation(randomInput1, randomInput2, Action.ADDITION, alertsMaker).findResult();
            long resultX1728 = randomInput1 + randomInput2;
            Assertions.assertEquals(resultX1728 / 1728, result.getFullPart());
            Assertions.assertEquals(resultX1728 % 1728, result.getFractionPartMultiplied1728());
        }

        @RepeatedTest(10)
        void checkSubtraction() {
            Result result = new MathOperation(randomInput1, randomInput2, Action.SUBTRACTION, alertsMaker).findResult();
            long resultX1728 = randomInput1 - randomInput2;
            Assertions.assertEquals(resultX1728 / 1728, result.getFullPart());
            Assertions.assertEquals(resultX1728 % 1728, result.getFractionPartMultiplied1728());
        }

        @RepeatedTest(10)
        void checkDivision() {
            Result result = new MathOperation(randomInput1, randomInput2, Action.DIVISION, alertsMaker).findResult();
            long resultX1728 = (randomInput1 * 1728) / randomInput2;
            Assertions.assertEquals(resultX1728 / 1728, result.getFullPart());
            Assertions.assertEquals(resultX1728 % 1728, result.getFractionPartMultiplied1728());
        }

        @RepeatedTest(10)
        void checkMultiplication() {
            Result result = new MathOperation(randomInput1, randomInput2, Action.MULTIPLICATION, alertsMaker).findResult();
            long resultX1728 = new BigInteger(String.valueOf(randomInput1)).multiply(BigInteger.valueOf(randomInput2))
                    .divide(BigInteger.valueOf(1728)).longValue();
            Assertions.assertEquals(resultX1728 / 1728, result.getFullPart());
            Assertions.assertEquals(resultX1728 % 1728, result.getFractionPartMultiplied1728());
        }
    }

    @Nested
    @DisplayName("Check for correct round after find result")
    class FindRoundTest {

        @ParameterizedTest
        @CsvFileSource(resources = "/round-after-division-test-data.csv", delimiter = ';', numLinesToSkip = 1)
        void roundAfterDivisionTest (long input1, long input2, String expected) {
            String round = new MathOperation(input1, input2, Action.DIVISION, alertsMaker).findRound();
            assertThat(round).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/round-after-multiplication-test-data.csv", delimiter = ';', numLinesToSkip = 1)
        void RoundAfterMultiplicationTest (long input1, long input2, String expected) {
            String round = new MathOperation(input1, input2, Action.MULTIPLICATION, alertsMaker).findRound();
            assertThat(round).isEqualTo(expected);
        }

    }

}
