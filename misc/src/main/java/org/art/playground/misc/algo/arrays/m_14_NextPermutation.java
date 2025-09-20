package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 */
public class m_14_NextPermutation {

    /**
     * Finds the next lexicographically greater permutation of the given array.
     * If no such permutation exists, rearranges array to the smallest permutation.
     * <p>
     * Algorithm steps:
     * 1. Find the rightmost character that is smaller than its next character (pivot)
     * 2. If no such character exists, the array is the largest permutation - reverse it
     * 3. Find the smallest character on right side of pivot that is larger than pivot
     * 4. Swap the pivot with this character
     * 5. Reverse the suffix starting at pivot+1 to get the next permutation
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // Step 1: Find the rightmost element that is smaller than its next element (pivot)
        // This is the position where we need to make a change to get next permutation
        int pivot = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i;
                break;
            }
        }

        // Step 2: If no pivot found, array is in descending order (largest permutation)
        // Convert it to the smallest permutation by reversing the entire array
        if (pivot == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // Step 3: Find the smallest element on the right side of pivot that is larger than pivot
        // This will be the element to swap with pivot to get next permutation
        int successor = nums.length - 1;
        while (nums[pivot] >= nums[successor]) {
            successor--;
        }

        // Step 4: Swap pivot with its successor
        swap(nums, pivot, successor);

        // Step 5: Reverse the suffix after pivot position to get the next permutation
        // The suffix is currently in descending order, reversing makes it ascending (smallest)
        reverse(nums, pivot + 1, nums.length - 1);
    }

    /**
     * Swaps two elements in the array at given indices.
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Reverses elements in the array between start and end indices (inclusive).
     * Used to convert descending order suffix to ascending order efficiently.
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    @Test
    void test0() {
        int[] arr = {1, 2, 3};

        nextPermutation(arr);

        assertThat(arr).isEqualTo(new int[]{1, 3, 2});
    }

    @Test
    void test1() {
        int[] arr = {3, 2, 1};

        nextPermutation(arr);

        assertThat(arr).isEqualTo(new int[]{1, 2, 3});
    }

    @Test
    void test2() {
        int[] arr = {1, 1, 5};

        nextPermutation(arr);

        assertThat(arr).isEqualTo(new int[]{1, 5, 1});
    }


    @Test
    void test3() {
        int[] arr = {1, 3, 2};

        nextPermutation(arr);

        assertThat(arr).isEqualTo(new int[]{2, 1, 3});
    }

    @Test
    void test4() {
        int[] arr = {4, 2, 0, 2, 3, 2, 0};

        nextPermutation(arr);

        assertThat(arr).isEqualTo(new int[]{4, 2, 0, 3, 0, 2, 2});
    }
}
