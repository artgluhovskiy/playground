package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Remove Duplicates from Sorted Array (LeetCode).
 * </p>
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates
 * in-place such that each unique element appears only once. The relative order of the
 * elements should be kept the same. Then return the number of unique elements in nums.
 * Consider the number of unique elements of nums to be k, to get accepted, you need
 * to do the following things:
 * Change the array nums such that the first k elements of nums contain the unique
 * elements in the order they were present in nums initially.
 * The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 */
public class e_1_RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int first = 1;

        for (int second = 1; second < nums.length; second++) {
            if(nums[second] != nums[second - 1]) {
                nums[first] = nums[second];
                first++;
            }
        }

        return first;
    }

    @Test
    void test1() {
        int[] input = {1, 1, 2};
        int[] expected = {1, 2};

        int actualUnique = removeDuplicates(input);

        assertThat(actualUnique).isEqualTo(expected.length);

        for (int i = 0; i < actualUnique; i++) {
            assertThat(input[i]).isEqualTo(expected[i]);
        }
    }

    @Test
    void test2() {
        int[] input = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] expected = {0, 1, 2, 3, 4};

        int actualUnique = removeDuplicates(input);

        assertThat(actualUnique).isEqualTo(expected.length);

        for (int i = 0; i < actualUnique; i++) {
            assertThat(input[i]).isEqualTo(expected[i]);
        }
    }

    @Test
    void test3() {
        int[] input = {1, 2, 3, 4, 5, 6};
        int[] expected = {1, 2, 3, 4, 5, 6};

        int actualUnique = removeDuplicates(input);

        assertThat(actualUnique).isEqualTo(expected.length);

        for (int i = 0; i < actualUnique; i++) {
            assertThat(input[i]).isEqualTo(expected[i]);
        }
    }

    @Test
    void test4() {
        int[] input = {1, 1, 2, 2};
        int[] expected = {1, 2};

        int actualUnique = removeDuplicates(input);

        assertThat(actualUnique).isEqualTo(expected.length);

        for (int i = 0; i < actualUnique; i++) {
            assertThat(input[i]).isEqualTo(expected[i]);
        }
    }
}
