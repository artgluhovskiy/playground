package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 * Use "Sieve of Eratosthenes".
 */
public class e_2_CountPrimes {

    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }

        int limit = (int) Math.sqrt(n);
        boolean[] composites = new boolean[n];
        for (int i = 2; i <= limit; i++) {
            if (!composites[i]) {
                for (int j = i * i; j < n; j = j + i) {
                    composites[j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!composites[i]) {
                count++;
            }
        }

        return count;
    }

    @Test
    void test0() {
        assertThat(countPrimes(10)).isEqualTo(4);
    }

    @Test
    void test1() {
        assertThat(countPrimes(0)).isEqualTo(0);
    }

    @Test
    void test2() {
        assertThat(countPrimes(1)).isEqualTo(0);
    }

    @Test
    void test3() {
        assertThat(countPrimes(3)).isEqualTo(1);
    }
}
