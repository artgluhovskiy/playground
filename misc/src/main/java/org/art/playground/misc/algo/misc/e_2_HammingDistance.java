package org.art.playground.misc.algo.misc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, return the Hamming distance between them.
 */
public class e_2_HammingDistance {

    public int hammingDistance(int x, int y) {
        int dist = 0;

        int xor = x ^ y;

        while (xor > 0) {
            if (xor % 2 == 1) {
                dist++;
            }
            xor = xor / 2;
        }

        return dist;
    }

    @Test
    void test0() {
        assertThat(hammingDistance(1, 4)).isEqualTo(2);
    }

    @Test
    void test1() {
        assertThat(hammingDistance(3, 1)).isEqualTo(1);
    }
}
