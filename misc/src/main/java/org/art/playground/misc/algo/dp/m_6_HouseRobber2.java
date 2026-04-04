package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
public class m_6_HouseRobber2 {

    public int rob(int[] nums) {
        int length = nums.length;

        if (length == 0) {
            return 0;
        }

        if (length == 1) {
            return nums[0];
        }

        return Math.max(
            robHelper(0, length - 2, nums),
            robHelper(1, length - 1, nums)
        );
    }

    private int robHelper(int from, int to, int[] nums) {
        if (from == to) {
            return nums[from];
        }

        int prevPrevMax = 0;
        int prevMax = nums[from];
        int max = prevMax;

        for (int i = from + 1; i <= to; i++) {
            int current = nums[i];
            int currentWithPrevPrev = current + prevPrevMax;
            if (currentWithPrevPrev > prevMax) {
                max = currentWithPrevPrev;
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
        var nums = new int[]{2, 3, 2};

        assertThat(rob(nums)).isEqualTo(3);
    }

    @Test
    void test1() {
        var nums = new int[]{1, 2, 3, 1};

        assertThat(rob(nums)).isEqualTo(4);
    }

    @Test
    void test2() {
        var nums = new int[]{1, 2, 3};

        assertThat(rob(nums)).isEqualTo(3);
    }
}
