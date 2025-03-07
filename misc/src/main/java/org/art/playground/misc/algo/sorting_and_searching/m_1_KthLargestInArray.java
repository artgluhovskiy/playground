package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
public class m_1_KthLargestInArray {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(0, nums.length - 1, nums, k);
    }

    private int quickSelect(int from, int to, int[] nums, int k) {
        int pivotPtr = from;
        int pivotVal = nums[to];

        for (int i = from; i < to; i++) {
            int val = nums[i];
            if (val < pivotVal) {
                swap(pivotPtr, i, nums);
                pivotPtr++;
            }
        }

        swap(pivotPtr, to, nums);

        int l = nums.length - k;
        if (pivotPtr > l) {
            return quickSelect(from, pivotPtr - 1, nums, k);
        } else if (pivotPtr < l) {
            return quickSelect(pivotPtr + 1, to, nums, k);
        } else {
            return nums[pivotPtr];
        }
    }

    private void swap(int from, int to, int[] nums) {
        int temp = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }

    public int findKthLargestOld(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);

        for (int i = 0; i < nums.length; i++) {
            priorityQueue.add(nums[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        return priorityQueue.peek();
    }

    @Test
    void test0() {
        int[] input = {3, 2, 1, 5, 6, 4};
        int k = 2;

        assertThat(findKthLargest(input, k)).isEqualTo(5);
    }

    @Test
    void test1() {
        int[] input = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;

        assertThat(findKthLargest(input, k)).isEqualTo(4);
    }

}
