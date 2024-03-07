package com.dreamlab.nexplorer.prime.impl;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import com.dreamlab.nexplorer.prime.PrimalityTest;

import org.springframework.stereotype.Component;

/**
 * Uses the Miller-Rabin primality test.
 * This is a strong probable prime test, which means that there is a probability
 * that n is composite. This algorithm uses multiple bases to make the probability
 * small.
 * If n is composite then running k iterations of the Miller–Rabin test will declare
 * n probably prime with a probability at most 4^(−k)
 * The complexity is O(k log^3 n), where k is the number of iterations where we
 * check n for compositeness. At the time of this writing k is 13, in which case
 * the probability that n is a pseudoprime (n is composite, but the test determines
 * falsely that it's a prime) is 4^(-13) ~ 0.00000149%
 */
@Component("millerRubinPrimalityTest")
public class MillerRabinPrimalityTest implements PrimalityTest {

    private final List<BigInteger> primes = Arrays.asList(
            BigInteger.valueOf(2),
            BigInteger.valueOf(3),
            BigInteger.valueOf(5),
            BigInteger.valueOf(7),
            BigInteger.valueOf(11),
            BigInteger.valueOf(13),
            BigInteger.valueOf(17),
            BigInteger.valueOf(19),
            BigInteger.valueOf(23),
            BigInteger.valueOf(29),
            BigInteger.valueOf(31),
            BigInteger.valueOf(37),
            BigInteger.valueOf(41)
    );

    @Override
    public boolean test(BigInteger n) {

        // if the number is non-positive, it's not prime
        if (n.compareTo(BigInteger.TWO) < 0) {
            return false;
        }

        // the Miller Rabin test is only valid for n > 2, so we have to check for
        // 2 separately
        if (n.compareTo(BigInteger.TWO) == 0) {
            return true;
        }

        // check if it's even
        if (n.getLowestSetBit() == 1) {
            return false;
        }

        // find n-1
        BigInteger nMinusOne = n.subtract(BigInteger.ONE);

        // n-1 can be represented as 2^s * d
        // first find s and d
        int s = 0;
        BigInteger d = nMinusOne;
        while (true) {
            // check if we can divide by 2
            BigInteger mod = d.mod(BigInteger.TWO);
            if (mod.compareTo(BigInteger.ZERO) != 0) {
                break;
            }
            d = d.divide(BigInteger.TWO);
            s++;
        }

        List<BigInteger> smallerThanNMinusOnePrimes = primes.stream()
                .filter(p -> p.compareTo(nMinusOne) < 0)
                .toList();

        for (BigInteger prime: smallerThanNMinusOnePrimes) {
            BigInteger remainder = prime.modPow(d, n);
            if (remainder.compareTo(BigInteger.ONE) != 0 && remainder.compareTo(nMinusOne) != 0) {
                int j = 0;
                while (j < s) {
                    remainder = remainder.modPow(BigInteger.TWO, n);
                    if (remainder.compareTo(nMinusOne) == 0) {
                        break;
                    }
                    j++;
                }
                if (j == s) {
                    return false;
                }
            }
        }

        return true;
    }
}
