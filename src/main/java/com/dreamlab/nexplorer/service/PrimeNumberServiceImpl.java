package com.dreamlab.nexplorer.service;

import com.dreamlab.nexplorer.prime.GreaterThanOrEqualToPrimeFinder;
import com.dreamlab.nexplorer.prime.PrimalityTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    @Autowired
    @Qualifier("optimalSelectivePrimalityTest")
    private PrimalityTest primalityTestDelegate;

    @Autowired
    private GreaterThanOrEqualToPrimeFinder greaterThanOrEqualToPrimeFinder;

    @Override
    public boolean isPrime(BigInteger n) {
        return primalityTestDelegate.test(n);
    }

    @Override
    public BigInteger nextPrime(BigInteger n) {
        return greaterThanOrEqualToPrimeFinder.find(n);
    }
}
