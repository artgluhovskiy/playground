package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 */
public class e_8_MoveZeros {

    public void moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return;
        }

        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }

    @Test
    void test0() {
        int[] input = {0, 1, 0, 3, 12};

        moveZeroes(input);

        assertThat(input).isEqualTo(new int[]{1, 3, 12, 0, 0});
    }

    @Test
    void test1() {
        int[] input = {0};

        moveZeroes(input);

        assertThat(input).isEqualTo(new int[]{0});
    }

    @Test
    void test2() {
        int[] input = {0, 0, 1};

        moveZeroes(input);

        assertThat(input).isEqualTo(new int[]{1, 0, 0});
    }
}
