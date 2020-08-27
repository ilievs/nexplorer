package com.dreamlab.nexplorer.prime.impl;

import com.dreamlab.nexplorer.prime.GreaterThanOrEqualToPrimeFinder;
import com.dreamlab.nexplorer.prime.PrimalityTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Tests each consecutive uneven number greater than N with the PrimalityTest
 * provided.
 *
 * The complexity is O( n/ln(n) * PrimalityTest ). In the case of a factorization
 * primality test the complexity will be O( 4/15 * n^2 / ln(n) ).
 */
@Component
public class ConsecutiveNumberTestingGreaterThanOrEqualToPrimeFinder
        implements GreaterThanOrEqualToPrimeFinder {

    private final PrimalityTest primalityTest;

    @Autowired
    public ConsecutiveNumberTestingGreaterThanOrEqualToPrimeFinder(
            @Qualifier("optimalSelectivePrimalityTest") PrimalityTest primalityTest) {
        this.primalityTest = primalityTest;
    }

    @Override
    public BigInteger find(BigInteger n) {
        if (n.bitLength() > 63) {
            throw new IllegalArgumentException("Integer too large for implementation");
        }
        long limitedN = n.longValue();
        // start from an uneven number
        limitedN = limitedN + 1 - limitedN % 2;
        for (long i = limitedN; i < Long.MAX_VALUE; i += 2) {
            // check the most common primes first and then do a full validation
            if (i % 3 != 0 && i % 5 != 0 && i % 7 != 0 &&
                    i % 11 != 0 && i % 13 != 0 && i % 17 != 0 &&
                    i % 19 != 0 && i % 23 != 0 && i % 29 != 0 &&
                    i % 31 != 0 && i % 37 != 0 && i % 41 != 0 &&
                    i % 43 != 0 && i % 47 != 0 && i % 53 != 0 &&
                    primalityTest.test(BigInteger.valueOf(i))) {
                return BigInteger.valueOf(i);
            }
        }
        throw new IllegalArgumentException(String.format("Maximum search limit of %d reached", Long.MAX_VALUE));
    }
}
