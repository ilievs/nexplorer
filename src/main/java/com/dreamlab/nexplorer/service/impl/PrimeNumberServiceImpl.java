package com.dreamlab.nexplorer.service.impl;

import java.math.BigInteger;

import com.dreamlab.nexplorer.prime.GreaterThanOrEqualToPrimeFinder;
import com.dreamlab.nexplorer.prime.PrimalityTest;
import com.dreamlab.nexplorer.service.PrimeNumberService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    private final PrimalityTest primalityTestDelegate;
    private final GreaterThanOrEqualToPrimeFinder greaterThanOrEqualToPrimeFinder;

    public PrimeNumberServiceImpl(@Qualifier("optimalSelectivePrimalityTest")
                                  PrimalityTest primalityTestDelegate,
                                  GreaterThanOrEqualToPrimeFinder greaterThanOrEqualToPrimeFinder) {

        this.primalityTestDelegate = primalityTestDelegate;
        this.greaterThanOrEqualToPrimeFinder = greaterThanOrEqualToPrimeFinder;
    }

    @Override
    public boolean isPrime(BigInteger n) {
        return primalityTestDelegate.test(n);
    }

    @Override
    public BigInteger nextPrime(BigInteger n) {
        return greaterThanOrEqualToPrimeFinder.find(n);
    }
}
