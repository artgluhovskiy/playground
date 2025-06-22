package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k)
 * such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 */
public class m_10_IncreasingTripletSubsequence {

    /**
     * Greedy approach:
     * The key insight is: "If I can prove a triplet exists with the smallest possible first two elements,
     * then a triplet definitely exists."
     */
    public boolean increasingTriplet(int[] nums) {
        float num_i = Float.POSITIVE_INFINITY;
        float num_j = Float.POSITIVE_INFINITY;

        for (int num : nums) {
            if (num <= num_i) {
                num_i = num;
            } else if (num <= num_j) {
                num_j = num;
            } else {
                return true;
            }
        }

        return false;
    }

    @Test
    void test0() {
        assertThat(increasingTriplet(new int[]{1, 2, 3, 4, 5})).isTrue();
    }

    @Test
    void test1() {
        assertThat(increasingTriplet(new int[]{5, 4, 3, 2, 1})).isFalse();
    }

    @Test
    void test2() {
        assertThat(increasingTriplet(new int[]{2, 1, 5, 0, 4, 6})).isTrue();
    }

    @Test
    void test3() {
        assertThat(increasingTriplet(new int[]{20, 100, 10, 12, 5, 13})).isTrue();
    }
}
