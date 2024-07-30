package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * Propose O(1) space solution.
 */
public class p3_RotateArray {

    public void rotate(int[] nums, int k) {
        // e.g. {1, 2, 3, 4, 5, 6, 7}, k = 3
        k = k % nums.length;

        // reversing full array
        // 7, 6, 5, 4, 3, 2, 1
        reverse(nums, 0, nums.length - 1);

        // reversing the left part
        // 5, 6, 7, 4, 3, 2, 1
        reverse(nums, 0, k - 1);

        // reversing the right part
        // 5, 6, 7, 1, 2, 3, 4
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int from, int to) {
        int i = from;
        int j = to;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    @Test
    void test0() {
        int[] input = {1, 2, 3, 4, 5, 6, 7};

        rotate(input, 3);

        int[] expected = {5, 6, 7, 1, 2, 3, 4};

        assertThat(input).isEqualTo(expected);
    }

    @Test
    void test1() {
        int[] input = {-1, -100, 3, 99};

        rotate(input, 2);

        int[] expected = {3, 99, -1, -100};

        assertThat(input).isEqualTo(expected);
    }

    @Test
    void test2() {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};

        rotate(input, 38);

        int[] expected = {17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        assertThat(input).isEqualTo(expected);
    }
}
