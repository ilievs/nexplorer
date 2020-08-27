package com.dreamlab.nexplorer.service;

import java.math.BigInteger;

public interface PrimeNumberService {

    boolean isPrime(BigInteger n);

    BigInteger nextPrime(BigInteger n);
}
