package org.art.playground.misc.algo.arrays;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 */
public class e_p4_ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        // Sacrifice computational complexity in favor of space
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    @Test
    void test0() {
        int[] input = {1, 2, 3, 1};

        assertThat(containsDuplicate(input)).isTrue();
    }

    @Test
    void test1() {
        int[] input = {1, 2, 3, 4};

        assertThat(containsDuplicate(input)).isFalse();
    }

    @Test
    void test2() {
        int[] input = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};

        assertThat(containsDuplicate(input)).isTrue();
    }

    @Test
    void test3() {
        int[] input = {3, 3};

        assertThat(containsDuplicate(input)).isTrue();
    }

}
