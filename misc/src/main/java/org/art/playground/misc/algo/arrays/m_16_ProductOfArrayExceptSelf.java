package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums, return an array answer such that answer[i]
 * is equal to the product of all the elements of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
public class m_16_ProductOfArrayExceptSelf {

    public int[] productExceptSelfV2(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }

        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int left = (i == 0) ? 1 : prefix[i - 1];
            int right = (i == n - 1) ? 1 : suffix[i + 1];
            result[i] = left * right;
        }

        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Initializing prefix right in the result array
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i];
        }

        // Computing the products
        int rightPrefix = 1;
        for (int i = n - 1; i >= 0; i--) {
            int left = (i > 0) ? result[i - 1] : 1;
            result[i] = left * rightPrefix;

            rightPrefix = rightPrefix * nums[i];
        }

        return result;
    }

    @Test
    void test0() {
        int[] input = {1, 2, 3, 4};
        int[] expected = {24, 12, 8, 6};

        assertThat(productExceptSelf(input)).isEqualTo(expected);
    }

    @Test
    void test1() {
        int[] input = {-1, 1, 0, -3, 3};
        int[] expected = {0, 0, 9, 0, 0};

        assertThat(productExceptSelf(input)).isEqualTo(expected);
    }
}
