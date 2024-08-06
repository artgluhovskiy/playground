package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 */
public class p3_MaximumSubArray {

    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int maxSum = nums[0];
        int prevSubArrayMaxSum = maxSum;

        for (int i = 1; i < nums.length; i++) {
            int currentVal = nums[i];
            int prevPlusCurrentMaxSum = prevSubArrayMaxSum + currentVal;

            if (prevPlusCurrentMaxSum > currentVal) {
                prevSubArrayMaxSum = prevPlusCurrentMaxSum;
            } else {
                prevSubArrayMaxSum = currentVal;
            }

            if (prevSubArrayMaxSum > maxSum) {
                maxSum = prevSubArrayMaxSum;
            }
        }

        return maxSum;
    }

    @Test
    void test0() {
        var nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        assertThat(maxSubArray(nums)).isEqualTo(6);
    }

    @Test
    void test1() {
        var nums = new int[]{1};

        assertThat(maxSubArray(nums)).isEqualTo(1);
    }

    @Test
    void test2() {
        var nums = new int[]{5, 4, -1, 7, 8};

        assertThat(maxSubArray(nums)).isEqualTo(23);
    }

    @Test
    void test3() {
        var nums = new int[]{-2, 1};

        assertThat(maxSubArray(nums)).isEqualTo(1);
    }
}
