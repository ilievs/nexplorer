package com.dreamlab.nexplorer.prime.impl;

import com.dreamlab.nexplorer.prime.PrimalityTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MillerRabinPrimalityTestTest {

    // Taken from https://primes.utm.edu/lists/2small/0bit.html
    // Each number of bits n is mapped to a list of positive integers k, such that
    // 2^n-k is a prime and is an n bit number
    static Map<Integer, int[]> K_MAPPING = Map.of(
            63, new int[]{25, 165, 259, 301, 375, 387, 391, 409, 457, 471},
            100, new int[]{15, 99, 153, 183, 267, 285, 357, 479, 603, 833},
            400, new int[]{593, 663, 767, 879, 1205, 2457, 3107, 3195, 3263, 3267});

    static final List<BigInteger> _63_BIT_PRIMES = getPrimes(63);
    static final List<BigInteger> _100_BIT_PRIME_NUMBERS = getPrimes(100);
    static final List<BigInteger> _400_BIT_PRIME_NUMBERS = getPrimes(400);

    private static final int MAX_TEST_NUMBERS = 5;

    static final List<BigInteger> OVER_100_DIGIT_NON_PRIME_NUMBERS = Arrays.asList(
            new BigInteger("3678617824563678216378678126378612783612783612786376231233523454353453453453453453268746237878326478"),
            new BigInteger("1861782367826781637867812637861278361278423612786376231233523454353453453453453453268746237878326475"),
            new BigInteger("8717236323678216786378678126378612783612783612786376231233523454353453453453453453268746237878326470"),
            new BigInteger("7823647267821637869837812637861278361278361278637623123352345435345345345345345326874612436878326476"),
        	new BigInteger("7823484274893216378671928126378612783612783612786376231233523454353453453453453453268746237878326472"),
            new BigInteger("8971347823678216378678128726378612783612783612786376231233523454353453453453453453268746237878326474")
    );

    static final List<BigInteger> _1000_BIT_NON_PRIME_NUMBERS = getRandomNonPrimeNumbers(10, MAX_TEST_NUMBERS);
    static final List<BigInteger> _4000_BIT_NON_PRIME_NUMBERS = getRandomNonPrimeNumbers(40, MAX_TEST_NUMBERS);

    static final BigInteger _63_BIT_PRIME = BigInteger.valueOf(18_014_398_241_046_527L);

    private final PrimalityTest primalityTest = new MillerRabinPrimalityTest();

    private static List<BigInteger> getPrimes(int nbits) {
        if (!K_MAPPING.containsKey(nbits)) {
            throw new IllegalArgumentException("nbits not supported");
        }
        int[] k = K_MAPPING.get(nbits);

        BigInteger twoPowN = BigInteger.TWO.pow(nbits);
        List<BigInteger> primes = new ArrayList<>();
        for (int value : k) {
            // we add one, because Long.MAX_VALUE = 2^63 - 1
            // and we first subtract and then add, so we don't
            // have an overflow
            primes.add(twoPowN.subtract(BigInteger.valueOf(value)));
        }

        return primes;
    }

    // TODO generalize the method below even further for
    //  generating random large non prime numbers
    public static List<BigInteger> getRandomNonPrimeNumbers(int multiplesOf100Bits, int numbersToGenerate) {
        if (numbersToGenerate < 1 || numbersToGenerate > 100) {
            throw new IllegalArgumentException("numbersToGenerate has to be between 1 and 100");
        }

        if (multiplesOf100Bits < 1 || multiplesOf100Bits > 1000) {
            throw new IllegalArgumentException("multiplesOf100Bits has to be between 1 and 1000");
        }

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        List<BigInteger> randomNonPrimes = new ArrayList<>();
        for (int i = 0; i < numbersToGenerate; i++) {
            BigInteger num = BigInteger.ONE;
            for (int j = 0; j < multiplesOf100Bits; j++) {
                int randIdx = random.nextInt(_100_BIT_PRIME_NUMBERS.size());
                num = num.multiply(_100_BIT_PRIME_NUMBERS.get(randIdx));
            }
            randomNonPrimes.add(num);
        }

        return randomNonPrimes;
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
        assertFalse(primalityTest.test(new BigInteger("-7824678236478236487236478236478236478236784623784623787823")));
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
    public void testVeryLargePrimes() {
        assertTrue(primalityTest.test(_63_BIT_PRIME));
        _63_BIT_PRIMES.forEach(p -> assertTrue(primalityTest.test(p)));
    }

    @Test
    public void testVeryLargeNonPrimes() {
        assertFalse(primalityTest.test(BigInteger.valueOf(1073676287L * 1073676287)));
        assertFalse(primalityTest.test(BigInteger.valueOf(7883L * 7901 * 7907 * 7919)));
        assertFalse(primalityTest.test(BigInteger.valueOf(1073676280)));
        assertFalse(primalityTest.test(BigInteger.valueOf(18014398241046520L)));
        assertFalse(primalityTest.test(BigInteger.valueOf(1152780769266106369L)));
    }

    @Test
    public void test100BitPrimes() {
        _100_BIT_PRIME_NUMBERS.forEach(num -> assertTrue(primalityTest.test(num)));
    }

    @Test
    public void test400BitPrimes() {
        _400_BIT_PRIME_NUMBERS.forEach(num -> assertTrue(primalityTest.test(num)));
    }

    @Test
    public void test100DigitNonPrimes() {
        OVER_100_DIGIT_NON_PRIME_NUMBERS.forEach(num -> assertFalse(primalityTest.test(num)));
    }

    @Test
    public void test1000BitNonPrimes() {
        _1000_BIT_NON_PRIME_NUMBERS.forEach(num -> assertFalse(primalityTest.test(num)));
    }

    @Test
    public void test4000BitNonPrimes() {
        _4000_BIT_NON_PRIME_NUMBERS.forEach(num -> assertFalse(primalityTest.test(num)));
    }
}