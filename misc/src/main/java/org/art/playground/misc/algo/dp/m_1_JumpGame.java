package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 */
public class m_1_JumpGame {

    // DP algorithm implementation
    public boolean canJumpDp(int[] nums) {
        boolean[] reachablePositions = new boolean[nums.length];
        reachablePositions[nums.length - 1] = true;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < reachablePositions.length && reachablePositions[i + j]) {
                    reachablePositions[i] = true;
                    break;
                }
            }
        }

        return reachablePositions[0];
    }

    // Greedy algorithm implementation
    public boolean canJumpGreedy(int[] nums) {
        int lastGoodIndex = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastGoodIndex) {
                lastGoodIndex = i;
            }
        }

        return lastGoodIndex == 0;
    }

    @Test
    void test0() {
        var nums = new int[]{2, 3, 1, 1, 4};

        assertThat(canJumpDp(nums)).isTrue();
    }

    @Test
    void test1() {
        var nums = new int[]{3, 2, 1, 0, 4};

        assertThat(canJumpDp(nums)).isFalse();
    }
}
