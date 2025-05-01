package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 */
public class m_4_LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {

            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] = max + 1;
        }

        int max = 0;
        for (int val : dp) {
            max = Math.max(max, val);
        }

        return max;
    }

    @Test
    void test0() {
        assertThat(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})).isEqualTo(4);
    }

    @Test
    void test1() {
        assertThat(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})).isEqualTo(4);
    }

    @Test
    void test2() {
        assertThat(lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7})).isEqualTo(1);
    }
}
