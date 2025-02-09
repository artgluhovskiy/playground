package org.art.playground.misc.algo.arrays;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 */
public class e_p9_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> paired = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int add = target - num;
            Integer index = paired.get(add);
            if (index != null) {
                return new int[]{index, i};
            } else {
                paired.put(num, i);
            }
        }

        return null;
    }

    @Test
    void test0() {
        int[] input = {2, 7, 11, 15};
        int target = 9;

        assertThat(twoSum(input, target)).isEqualTo(new int[]{0, 1});
    }

    @Test
    void test1() {
        int[] input = {3, 2, 4};
        int target = 6;

        assertThat(twoSum(input, target)).isEqualTo(new int[]{1, 2});
    }

    @Test
    void test2() {
        int[] input = {3, 3};
        int target = 6;

        assertThat(twoSum(input, target)).isEqualTo(new int[]{0, 1});
    }

    @Test
    void test3() {
        int[] input = {0, 4, 3, 0};
        int target = 0;

        assertThat(twoSum(input, target)).isEqualTo(new int[]{0, 3});
    }
}
