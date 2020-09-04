package com.dreamlab.nexplorer.prime.impl;

import com.dreamlab.nexplorer.prime.PrimalityTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FactorizationCheckingPrimalityTestTest {

    public static final BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
    public static final BigInteger LARGEST_PRIME_SMALLER_THAN_LONG_MAX_VALUE =
            BigInteger.valueOf(9223372036854775783L);

    private final PrimalityTest primalityTest = new FactorizationCheckingPrimalityTest();

    @Test
    public void testNegativeValues() {
        assertFalse(primalityTest.test(BigInteger.valueOf(-1273)));
        assertFalse(primalityTest.test(BigInteger.valueOf(-6747328971283L)));
        assertFalse(primalityTest.test(new BigInteger("-65635463463746183748374871384713847843874897")));
        assertFalse(primalityTest.test(new BigInteger("-84713894789234734792379791239891238912389128493578475")));
    }

    @Test
    public void testZero() {
        assertFalse(primalityTest.test(BigInteger.ZERO));
    }

    @Test
    public void testOne() {
        assertFalse(primalityTest.test(BigInteger.ONE));
    }

    @Test
    public void testTwo() {
        assertTrue(primalityTest.test(BigInteger.TWO));
    }

    @Test
    public void testNonPositiveNumber() {
        assertFalse(primalityTest.test(BigInteger.valueOf(-1283782)));
        assertFalse(primalityTest.test(BigInteger.valueOf(-12837822534545L)));
    }

    @Test
    public void testLongMaxValue() {
        assertFalse(primalityTest.test(LONG_MAX_VALUE));
    }

    @Test
    public void testLimitReachedWithNumberGreaterThanLongMaxValue() {
        assertThrows(IllegalArgumentException.class,
                () -> assertFalse(primalityTest.test(LONG_MAX_VALUE.add(BigInteger.ONE))));

        assertThrows(IllegalArgumentException.class,
                () -> assertFalse(primalityTest.test(LONG_MAX_VALUE.add(BigInteger.valueOf(123)))));
    }

    @Test
    public void testLimitReachedWithNumberGreaterThanLargestPrimeSmallerThanLongMaxValue() {
        assertThrows(IllegalArgumentException.class,
                () -> primalityTest.test(LARGEST_PRIME_SMALLER_THAN_LONG_MAX_VALUE));
    }

    @Test
    public void testSmallPrimes() {
        assertTrue(primalityTest.test(BigInteger.valueOf(17)));
        assertTrue(primalityTest.test(BigInteger.valueOf(23)));
        assertTrue(primalityTest.test(BigInteger.valueOf(31)));
        assertTrue(primalityTest.test(BigInteger.valueOf(41)));
        assertTrue(primalityTest.test(BigInteger.valueOf(47)));
        assertTrue(primalityTest.test(BigInteger.valueOf(61)));
        assertTrue(primalityTest.test(BigInteger.valueOf(53)));
        assertTrue(primalityTest.test(BigInteger.valueOf(59)));
    }

    @Test
    public void testSmallNonPrimes() {
        assertFalse(primalityTest.test(BigInteger.valueOf(122)));
        assertFalse(primalityTest.test(BigInteger.valueOf(999)));
        assertFalse(primalityTest.test(BigInteger.valueOf(18)));
        assertFalse(primalityTest.test(BigInteger.valueOf(410)));
        assertFalse(primalityTest.test(BigInteger.valueOf(475)));
        assertFalse(primalityTest.test(BigInteger.valueOf(32)));
        assertFalse(primalityTest.test(BigInteger.valueOf(30)));
        assertFalse(primalityTest.test(BigInteger.valueOf(64)));
    }

    @Test
    public void testThousandPrimes() {
        assertTrue(primalityTest.test(BigInteger.valueOf(117239)));
        assertTrue(primalityTest.test(BigInteger.valueOf(127031)));
        assertTrue(primalityTest.test(BigInteger.valueOf(138937)));
        assertTrue(primalityTest.test(BigInteger.valueOf(141079)));
        assertTrue(primalityTest.test(BigInteger.valueOf(267017)));
        assertTrue(primalityTest.test(BigInteger.valueOf(269987)));
        assertTrue(primalityTest.test(BigInteger.valueOf(374321)));
    }

    @Test
    public void testThousandNonPrimes() {
        assertFalse(primalityTest.test(BigInteger.valueOf(117232)));
        assertFalse(primalityTest.test(BigInteger.valueOf(127030)));
        assertFalse(primalityTest.test(BigInteger.valueOf(138935)));
        assertFalse(primalityTest.test(BigInteger.valueOf(141072)));
        assertFalse(primalityTest.test(BigInteger.valueOf(267015)));
        assertFalse(primalityTest.test(BigInteger.valueOf(269980)));
        assertFalse(primalityTest.test(BigInteger.valueOf(374322)));
    }

    @Test
    public void testBillionPrimes() {
        assertTrue(primalityTest.test(BigInteger.valueOf(274_876_858_367L)));
        assertTrue(primalityTest.test(BigInteger.valueOf(68_718_952_447L)));
        assertTrue(primalityTest.test(BigInteger.valueOf(1_073_676_287)));
    }

    @Test
    public void testVeryLargePrime() {
        assertTrue(primalityTest.test(BigInteger.valueOf(18_014_398_241_046_527L)));
    }

    @Test
    public void testVeryLargeNonPrimes() {
        assertFalse(primalityTest.test(BigInteger.valueOf(1073676287L * 1073676287)));
        assertFalse(primalityTest.test(BigInteger.valueOf(7883L * 7901 * 7907 * 7919)));
        assertFalse(primalityTest.test(BigInteger.valueOf(1073676280)));
        assertFalse(primalityTest.test(BigInteger.valueOf(18014398241046520L)));
        assertFalse(primalityTest.test(BigInteger.valueOf(1152780769266106369L)));
    }
}