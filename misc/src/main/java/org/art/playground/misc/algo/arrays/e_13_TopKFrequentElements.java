package org.art.playground.misc.algo.arrays;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 */
public class e_13_TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : nums) {
            counts.merge(value, 1, Integer::sum);
        }

        // index of the array is a frequency,
        // the actual value is the value(s) with such frequency
        Set<Integer>[] values = new Set[nums.length + 1];
        for (int i = 0; i < values.length; i++) {
            values[i] = new HashSet<>();
        }

        counts.forEach((value, frequency) -> values[frequency].add(value));

        int[] result = new int[k];
        int i = 0;
        int j = values.length - 1;
        while (i < result.length && j > 0) {
            Set<Integer> vals = values[j];
            if (!vals.isEmpty()) {
                for (Integer val : vals) {
                    result[i] = val;
                    i++;
                }
            }
            j--;
        }

        return result;
    }

    @Test
    void test0() {
        int[] input = new int[] {1, 1, 1, 2, 2, 3};
        int k = 2;
        assertThat(topKFrequent(input, k)).isEqualTo(new int[] {1, 2});
    }

    @Test
    void test1() {
        int[] input = new int[] {1};
        int k = 1;
        assertThat(topKFrequent(input, k)).isEqualTo(new int[] {1});
    }
}
