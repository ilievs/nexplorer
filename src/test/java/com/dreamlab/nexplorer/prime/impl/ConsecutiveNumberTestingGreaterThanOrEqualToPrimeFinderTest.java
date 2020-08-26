package com.dreamlab.nexplorer.prime.impl;

import com.dreamlab.nexplorer.prime.GreaterThanOrEqualToPrimeFinder;
import com.dreamlab.nexplorer.prime.PrimalityTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ConsecutiveNumberTestingGreaterThanOrEqualToPrimeFinderTest {

    PrimalityTest primalityTest = new FactorizationCheckingPrimalityTest();
    GreaterThanOrEqualToPrimeFinder greaterThanNPrimeFinder =
            new ConsecutiveNumberTestingGreaterThanOrEqualToPrimeFinder(primalityTest);

    @Test
    public void testSmallNumbers() {
        assertEquals(2129, greaterThanNPrimeFinder.find(BigInteger.valueOf(2128)));
        assertEquals(2131, greaterThanNPrimeFinder.find(BigInteger.valueOf(2130)));
    }

    @Test
    public void testLargeNumber() {
        assertEquals(70368760954879L, greaterThanNPrimeFinder.find(BigInteger.valueOf(70368760954878L)));
    }

    @Test
    public void testVeryLargeNumber() {
        long n = 7036876095487800000L;
        long next = greaterThanNPrimeFinder.find(BigInteger.valueOf(n));
        assertTrue(primalityTest.test(BigInteger.valueOf(next)));
    }

    @Test
    public void testLargePrimeRightAfterGivenN() {
        long largePrime = 3318308475676071413L;
        long next = greaterThanNPrimeFinder.find(BigInteger.valueOf(largePrime-1));
        assertEquals(largePrime, next);
    }
}