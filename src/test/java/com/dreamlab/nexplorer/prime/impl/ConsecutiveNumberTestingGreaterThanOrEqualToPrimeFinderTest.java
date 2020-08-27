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
        assertEquals(BigInteger.valueOf(2129), greaterThanNPrimeFinder.find(BigInteger.valueOf(2128)));
        assertEquals(BigInteger.valueOf(2131), greaterThanNPrimeFinder.find(BigInteger.valueOf(2130)));
    }

    @Test
    public void testLargeNumber() {
        assertEquals(BigInteger.valueOf(70368760954879L), greaterThanNPrimeFinder.find(BigInteger.valueOf(70368760954878L)));
    }

    @Test
    public void testVeryLargeNumber() {
        BigInteger n = BigInteger.valueOf(7036876095487800000L);
        BigInteger next = greaterThanNPrimeFinder.find(n);
        assertTrue(primalityTest.test(next));
    }

    @Test
    public void testLargePrimeRightAfterGivenN() {
        BigInteger largePrime = BigInteger.valueOf(3318308475676071413L);
        BigInteger next = greaterThanNPrimeFinder.find(largePrime.subtract(BigInteger.ONE));
        assertEquals(largePrime, next);
    }
}