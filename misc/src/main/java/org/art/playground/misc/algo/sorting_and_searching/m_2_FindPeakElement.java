package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 * Given a 0-indexed integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always
 * considered to be strictly greater than a neighbor that is outside the array.
 * You must write an algorithm that runs in O(log n) time.
 */
public class m_2_FindPeakElement {

    public int findPeakElement(int[] nums) {
        return findPeakHelper(0, nums.length - 1, nums);
    }

    private int findPeakHelper(int from, int to, int[] nums) {
        if (from == to) {
            return from;
        }

        int pivotIdx = from + (to - from) / 2;
        int pivotVal = nums[pivotIdx];

        int leftVal;
        if (pivotIdx - 1 < 0) {
            leftVal = Integer.MIN_VALUE;
        } else {
            leftVal = nums[pivotIdx - 1];
        }

        int rightVal;
        if (pivotIdx + 1 >= nums.length) {
            rightVal = Integer.MIN_VALUE;
        } else {
            rightVal = nums[pivotIdx + 1];
        }

        if (leftVal < pivotVal && rightVal < pivotVal) {
            return pivotIdx;
        } else if (leftVal > pivotVal) {
            return findPeakHelper(from, pivotIdx - 1, nums);
        } else {
            return findPeakHelper(pivotIdx + 1, to, nums);
        }
    }

    @Test
    void test0() {
        int[] input = {1, 2, 3, 1};

        assertThat(findPeakElement(input)).isEqualTo(2);
    }

    @Test
    void test1() {
        int[] input = {1, 2, 1, 3, 5, 6, 4};

        assertThat(findPeakElement(input)).isEqualTo(5);
    }

    @Test
    void test2() {
        int[] input = {1};

        assertThat(findPeakElement(input)).isEqualTo(0);
    }

    @Test
    void test3() {
        int[] input = {1, 2};

        assertThat(findPeakElement(input)).isEqualTo(1);
    }

}
