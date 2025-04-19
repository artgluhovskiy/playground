package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */
public class m_6_SubArraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int result = 0;

        Map<Integer, Integer> prefixSum = new HashMap<>();

        int sum = 0;

        for (int num : nums) {
            sum += num;

            if (sum == k) {
                result++;
            }

            int overhead = sum - k;

            if (prefixSum.containsKey(overhead)) {
                result += prefixSum.get(overhead);
            }

            prefixSum.merge(sum, 1, Integer::sum);
        }

        return result;
    }

    @Test
    void test0() {
        var input = new int[]{1, 2, 3};

        assertThat(subarraySum(input, 3)).isEqualTo(2);
    }

    @Test
    void test1() {
        var input = new int[]{1, 1, 1};

        assertThat(subarraySum(input, 2)).isEqualTo(2);
    }
}
