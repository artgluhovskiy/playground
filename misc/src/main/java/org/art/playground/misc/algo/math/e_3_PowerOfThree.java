package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 */
public class e_3_PowerOfThree {

    public boolean isPowerOfThree(int n) {
        int maxPossiblePowerOfThree = (int) Math.pow(3, 19);
        return n > 0 && maxPossiblePowerOfThree % n == 0;
    }

    @Test
    void test0() {
        assertThat(isPowerOfThree(27)).isTrue();
    }

    @Test
    void test1() {
        assertThat(isPowerOfThree(0)).isFalse();
    }

    @Test
    void test2() {
        assertThat(isPowerOfThree(-1)).isFalse();
    }
}
