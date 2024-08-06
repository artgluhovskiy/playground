package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
public class p4_HouseRobber {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int max = nums[0];
        int prevMax = max;
        int prevPrevMax = 0;

        for (int i = 1; i < nums.length; i++) {
            int currentValue = nums[i];
            int prevPrevPlusCurrent = prevPrevMax + currentValue;
            if (prevPrevPlusCurrent > prevMax) {
                max = prevPrevPlusCurrent;
            } else {
                max = prevMax;
            }
            prevPrevMax = prevMax;
            prevMax = max;
        }

        return max;
    }

    @Test
    void test0() {
        var nums = new int[]{1, 2, 3, 1};

        assertThat(rob(nums)).isEqualTo(4);
    }

    @Test
    void test1() {
        var nums = new int[]{2, 7, 9, 3, 1};

        assertThat(rob(nums)).isEqualTo(12);
    }

    @Test
    void test2() {
        var nums = new int[]{8, 1, 1, 9, 1};

        assertThat(rob(nums)).isEqualTo(17);
    }
}
