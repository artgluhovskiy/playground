package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given a large integer represented as an integer array digits,
 * where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order.
 * The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 */
public class p7_PlusOne {

    public int[] plusOne(int[] digits) {
        return doPlusOne(digits, digits.length - 1);
    }

    private int[] doPlusOne(int[] digits, int pos) {
        if (pos < 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            System.arraycopy(digits, 0, newDigits, 1, digits.length);
            return newDigits;
        }

        int currentDigit = digits[pos];

        if (currentDigit < 9) {
            digits[pos] = currentDigit + 1;
            return digits;
        } else {
            digits[pos] = 0;
            return doPlusOne(digits, --pos);
        }
    }

    @Test
    void test0() {
        int[] input = {1, 2, 3};

        assertThat(plusOne(input)).isEqualTo(new int[]{1, 2, 4});
    }

    @Test
    void test1() {
        int[] input = {4, 3, 2, 1};

        assertThat(plusOne(input)).isEqualTo(new int[]{4, 3, 2, 2});
    }

    @Test
    void test2() {
        int[] input = {9};

        assertThat(plusOne(input)).isEqualTo(new int[]{1, 0});
    }

    @Test
    void test3() {
        int[] input = {9, 9, 9};

        assertThat(plusOne(input)).isEqualTo(new int[]{1, 0, 0, 0});
    }
}
