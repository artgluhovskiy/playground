package org.art.playground.misc.algo.misc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function that takes the binary representation of a positive integer and
 * returns the number of set bits it has (also known as the Hamming weight).
 */
public class e_1_NumberOfOneBits {

    public int hammingWeight(int n) {
        int bits = 0;
        int current = n;

        while (current > 0) {
            if (current % 2 == 1) {
                bits++;
            }
            current = current / 2;
        }

        return bits;
    }

    @Test
    void test0() {
        assertThat(hammingWeight(11)).isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(hammingWeight(128)).isEqualTo(1);
    }

    @Test
    void test2() {
        assertThat(hammingWeight(2147483645)).isEqualTo(30);
    }
}
