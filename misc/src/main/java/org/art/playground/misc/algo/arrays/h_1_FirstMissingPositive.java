package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 */
public class h_1_FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            int idx = val - 1;

            if (idx < 0 || idx > nums.length - 1) {
                continue;
            }

            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            } else if (nums[idx] == 0) {
                nums[idx] = -(nums.length + 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    @Test
    void test0() {
        assertThat(firstMissingPositive(new int[]{1, 2, 0})).isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(firstMissingPositive(new int[]{3, 4, -1, 1})).isEqualTo(2);
    }

    @Test
    void test2() {
        assertThat(firstMissingPositive(new int[]{7, 8, 9, 11, 12})).isEqualTo(1);
    }

    @Test
    void test3() {
        assertThat(firstMissingPositive(new int[]{1})).isEqualTo(2);
    }
}
