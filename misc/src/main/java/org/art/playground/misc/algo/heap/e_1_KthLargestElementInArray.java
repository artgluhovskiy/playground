package org.art.playground.misc.algo.heap;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Can you solve it without sorting?
 * <p>
 * Quick Select Algorithm Impl
 */
public class e_1_KthLargestElementInArray {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(0, nums.length - 1, nums, k);
    }

    private int quickSelect(int from, int to, int[] nums, int k) {
        int pivotPtr = from;
        int pivot = nums[to];
        for (int i = from; i < to; i++) {
            int value = nums[i];
            if (value < pivot) {
                swap(i, pivotPtr, nums);
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

    public int findKthLargestEasy(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k + 1);

        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    @Test
    void test0() {
        assertThat(findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2)).isEqualTo(5);
    }

    @Test
    void test1() {
        assertThat(findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)).isEqualTo(4);
    }
}
