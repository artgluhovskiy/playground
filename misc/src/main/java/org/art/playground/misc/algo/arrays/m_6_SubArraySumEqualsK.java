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

    /**
     * Counts the number of contiguous subarrays whose sum equals k.
     * Uses prefix sum technique with HashMap for O(n) solution.
     * <p>
     * Key insight: If prefix_sum[j] - prefix_sum[i] = k, then subarray[i+1...j] has sum k
     * Therefore: prefix_sum[i] = prefix_sum[j] - k (we look for this in our map)
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the HashMap
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;

        // Map to store frequency of prefix sums
        Map<Integer, Integer> prefixSumFrequency = new HashMap<>();

        for (int num : nums) {
            // Update running sum (prefix sum up to current index)
            currentSum += num;

            // Case 1: Subarray from start to current index has sum = k
            if (currentSum == k) {
                count++;
            }

            // Case 2: Check if there exists a previous prefix sum such that
            // currentSum - previousPrefixSum = k
            // Which means: previousPrefixSum = currentSum - k
            int targetPrefixSum = currentSum - k;

            if (prefixSumFrequency.containsKey(targetPrefixSum)) {
                count += prefixSumFrequency.get(targetPrefixSum);
            }

            // Store current prefix sum in the map for future references
            prefixSumFrequency.merge(currentSum, 1, Integer::sum);
        }

        return count;
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
