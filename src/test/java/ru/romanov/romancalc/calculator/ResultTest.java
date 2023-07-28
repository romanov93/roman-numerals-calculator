package ru.romanov.romancalc.calculator;

import org.junit.jupiter.api.*;

import java.util.concurrent.ThreadLocalRandom;

@TestMethodOrder(MethodOrderer.Random.class)
class ResultTest {


    @RepeatedTest(5)
    void gettersShouldReturnIntsWasInput() {
        int fullPart = ThreadLocalRandom.current().nextInt(0, 3999999);
        int fractionPart = ThreadLocalRandom.current().nextInt(0, 1727);
        Result result = new Result(fullPart, fractionPart);
        Assertions.assertEquals(fullPart, result.getFullPart());
        Assertions.assertEquals(fractionPart, result.getFractionPartMultiplied1728());
    }

    @Test
    void resultWithBothZeroPartsShouldBeZero() {
        Result result = new Result(0, 0);
        Assertions.assertTrue(result.isZero());
    }

    @Test
    void resultWithNotZeroFullPartShouldNotBeZero() {
        Result result = new Result(1, 0);
        Assertions.assertFalse(result.isZero());
    }

    @Test
    void resultWithNotZeroFractionPartShouldNotBeZero() {
        Result result = new Result(0, 1);
        Assertions.assertFalse(result.isZero());
    }

}
