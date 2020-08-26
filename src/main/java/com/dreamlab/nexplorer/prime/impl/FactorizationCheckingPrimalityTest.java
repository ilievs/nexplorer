package com.dreamlab.nexplorer.prime.impl;

import com.dreamlab.nexplorer.prime.PrimalityTest;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * This implementation tries to factorize the given number to find
 * out if it's a prime.
 *
 * The complexity is O( 4/15 n ), because we're skipping most numbers
 * with the knowledge that numbers can be represented with the form:
 *
 * 30k + i, where
 *
 * 30 is 2 * 3 * 5, which are the first 3 primes and will be used to skip numbers
 * that factor out with them, e.g. 30 * 1 + 3 = 3 * (10 * 1 + 1), so we know the
 * number is a multiple of 3 and we can skip it.
 *
 * i = 0, (1), 2, 3, 4, 5, 6, (7), 8, 9, 10, (11), 12, (13), 14, 15, 16, (17), 18,
 *      (19), 20, 21, 22, (23), 24, 25, 26, 27, 28, (29)
 *
 * The numbers in brackets are the ones that can't be factored out with the factors
 * of 30, so we have to check them. Using this formula we need to check only 8/30
 * (or 4/15) of all numbers from 2 to sqrt(n), because out of the 30 numbers between
 * 30k + i and 30(k+1) + i there are 8 that have to be checked. This can be optimized
 * further with more primes.
 */
@Component
public class FactorizationCheckingPrimalityTest implements PrimalityTest {


    @Override
    public boolean test(BigInteger n) {
        if (n.bitLength() > 63) {
            throw new IllegalArgumentException("Integer too large for implementation");
        }
        long limitedN = n.longValue();
        if (limitedN == 2 || limitedN == 3 || limitedN == 5 ||
                limitedN == 7 || limitedN == 11 || limitedN == 13 ||
                limitedN == 17 || limitedN == 19 || limitedN == 23 ||
                limitedN == 29) {
            return true;
        }

        if (limitedN % 2 == 0 || limitedN % 3 == 0 || limitedN % 5 == 0 ||
                limitedN % 7 == 0 || limitedN % 11 == 0 || limitedN % 13 == 0 ||
                limitedN % 17 == 0 || limitedN % 19 == 0 || limitedN % 23 == 0 ||
                limitedN % 29 == 0) {
            return false;
        }

        for (long i = 30; i * i <= limitedN; i += 30) {
            if (limitedN % (i + 1) == 0 || limitedN % (i + 7) == 0 ||
                    limitedN % (i + 11) == 0 || limitedN % (i + 13) == 0 ||
                    limitedN % (i + 17) == 0 || limitedN % (i + 19) == 0 ||
                    limitedN % (i + 23) == 0 || limitedN % (i + 29) == 0) {
                return false;
            }
        }

        return true;
    }
}
