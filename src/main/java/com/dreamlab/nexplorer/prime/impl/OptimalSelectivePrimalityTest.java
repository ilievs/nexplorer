package com.dreamlab.nexplorer.prime.impl;

import com.dreamlab.nexplorer.prime.PrimalityTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component("optimalSelectivePrimalityTest")
public class OptimalSelectivePrimalityTest implements PrimalityTest {

    @Autowired
    @Qualifier("millerRubinPrimalityTest")
    private PrimalityTest millerRubin;

    @Autowired
    @Qualifier("factorizationCheckingPrimalityTest")
    private PrimalityTest factorizationCheckingPrimalityTest;

    @Override
    public boolean test(BigInteger n) {
        if (n.bitLength() <= 63) {
            return factorizationCheckingPrimalityTest.test(n);
        } else {
            return millerRubin.test(n);
        }
    }
}
