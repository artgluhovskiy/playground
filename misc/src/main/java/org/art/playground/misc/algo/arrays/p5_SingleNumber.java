package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
public class p5_SingleNumber {

    /*
     * XOR operation property is used here.
     * Duplicated values are eliminated and only single one is left.
     * The order of XOR-ing doesn't matter.
     *
     * Some useful XOR props:
     * n ^ 0 = n
     * n ^ n = 0
     * associativity + commutativity: ((((4 ^ 1 ) ^ 2 ) ^ 1 ) ^ 2 ) = (2 ^ 2) ^ (1 ^ 1) ^ 4
     */
    public int singleNumber(int[] nums) {
        int res = 0;

        for (int num : nums) {
            res = res ^ num;
        }

        return res;
    }

    @Test
    void test0() {
        int[] input = {2, 2, 1};

        assertThat(singleNumber(input)).isEqualTo(1);
    }

    @Test
    void test1() {
        int[] input = {4, 1, 2, 1, 2};

        assertThat(singleNumber(input)).isEqualTo(4);
    }

    @Test
    void test2() {
        int[] input = {1};

        assertThat(singleNumber(input)).isEqualTo(1);
    }
}
