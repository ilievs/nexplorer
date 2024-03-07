package com.dreamlab.nexplorer.prime.impl;

import java.math.BigInteger;

import com.dreamlab.nexplorer.prime.PrimalityTest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("optimalSelectivePrimalityTest")
public class OptimalSelectivePrimalityTest implements PrimalityTest {

    private final PrimalityTest millerRubin;
    private final PrimalityTest factorizationPrimalityTest;

    public OptimalSelectivePrimalityTest(@Qualifier("millerRubinPrimalityTest")
                                         PrimalityTest millerRubin,
                                         @Qualifier("factorizationPrimalityTest")
                                         PrimalityTest factorizationPrimalityTest) {
        this.millerRubin = millerRubin;
        this.factorizationPrimalityTest = factorizationPrimalityTest;
    }

    @Override
    public boolean test(BigInteger n) {
        if (n.bitLength() <= 63) {
            return factorizationPrimalityTest.test(n);
        } else {
            return millerRubin.test(n);
        }
    }
}
