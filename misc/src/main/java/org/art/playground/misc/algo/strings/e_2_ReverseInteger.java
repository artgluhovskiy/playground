package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 */
public class e_2_ReverseInteger {

    public int reverse(int x) {
        int result = x % 10;
        int quotient = x / 10;

        while (quotient != 0) {
            if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int reminder = quotient % 10;
            result = result * 10 + reminder;
            quotient = quotient / 10;
        }

        return result;
    }

    @Test
    void test0() {
        int input = 123;

        assertThat(reverse(input)).isEqualTo(321);
    }

    @Test
    void test1() {
        int input = 120;

        assertThat(reverse(input)).isEqualTo(21);
    }

    @Test
    void test2() {
        int input = -123;

        assertThat(reverse(input)).isEqualTo(-321);
    }

    @Test
    void test3() {
        int input = -1234;

        assertThat(reverse(input)).isEqualTo(-4321);
    }

    @Test
    void test4() {
        int input = -1;

        assertThat(reverse(input)).isEqualTo(-1);
    }

    @Test
    void test5() {
        int input = 1534236469;

        assertThat(reverse(input)).isEqualTo(0);
    }

}
