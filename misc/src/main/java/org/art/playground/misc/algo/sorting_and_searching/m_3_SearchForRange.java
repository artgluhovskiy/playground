package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class m_3_SearchForRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        return new int[]{
            searchLeft(0, nums.length - 1, nums, target),
            searchRight(0, nums.length - 1, nums, target)
        };
    }

    private int searchLeft(int from, int to, int[] nums, int target) {
        int midIdx = from + (to - from) / 2;
        int midVal = nums[midIdx];

        if (from >= to && midVal != target) {
            return -1;
        }

        if (midVal == target && (midIdx == 0 || nums[midIdx - 1] != midVal)) {
            return midIdx;
        } else if (midVal >= target) {
            return searchLeft(from, midIdx - 1, nums, target);
        } else {
            return searchLeft(midIdx + 1, to, nums, target);
        }
    }

    private int searchRight(int from, int to, int[] nums, int target) {
        int midIdx = from + (to - from) / 2;
        int midVal = nums[midIdx];

        if (from >= to && midVal != target) {
            return -1;
        }

        if (midVal == target && (midIdx == nums.length - 1 || nums[midIdx + 1] != midVal)) {
            return midIdx;
        } else if (midVal > target) {
            return searchRight(from, midIdx - 1, nums, target);
        } else {
            return searchRight(midIdx + 1, to, nums, target);
        }
    }

    @Test
    void test0() {
        int[] input = {5, 7, 7, 8, 8, 10};

        assertThat(searchRange(input, 8)).isEqualTo(new int[]{3, 4});
    }

    @Test
    void test1() {
        int[] input = {5, 7, 7, 8, 8, 10};

        assertThat(searchRange(input, 6)).isEqualTo(new int[]{-1, -1});
    }

    @Test
    void test2() {
        int[] input = {};

        assertThat(searchRange(input, 0)).isEqualTo(new int[]{-1, -1});
    }

    @Test
    void test3() {
        int[] input = {2, 2};

        assertThat(searchRange(input, 2)).isEqualTo(new int[]{0, 1});
    }

}
