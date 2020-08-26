package com.dreamlab.nexplorer.prime;

import java.util.BitSet;

public class Utils {

    public static BitSet getPrimeSieveUpTo(int n) {
        BitSet sieve = new BitSet(n);
        sieve.set(0);
        sieve.set(1);
        // n could be sufficiently large to have an overflow, so loop with overflow detection
        for (int i = 2; i > 0 && i <= n; i++) {
            if (!sieve.get(i)) {
                for (int j = i * i; j > 0 && j <= n; j += i) {
                    sieve.set(j);
                }
            }
        }
        return sieve;
    }
}
