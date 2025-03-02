package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums and an integer k,
 * return the k most frequent elements. You may return the answer in any order.
 */
public class m_4_TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.merge(num, 1, Integer::sum);
        }

        Set<Integer>[] freqsInverted = new Set[nums.length + 1];

        for (int i = 0; i < freqsInverted.length; i++) {
            freqsInverted[i] = new HashSet<>();
        }

        counts.forEach((val, count) -> freqsInverted[count].add(val));

        int[] topK = new int[k];

        int i = 0;
        int j = freqsInverted.length - 1;
        while (i < k && j >= 0) {
            for (Integer val : freqsInverted[j]) {
                topK[i] = val;
                i++;
            }
            j--;
        }

        return topK;
    }

    @Test
    void test1() {
        int[] input = new int[]{1, 1, 1, 2, 2, 3};

        int[] result = topKFrequent(input, 2);

        assertThat(result).containsExactlyInAnyOrder(1, 2);
    }

    @Test
    void test2() {
        int[] input = new int[]{1};

        int[] result = topKFrequent(input, 1);

        assertThat(result).containsExactlyInAnyOrder(1);
    }

    @Test
    void test3() {
        int[] input = new int[]{-1, -1};

        int[] result = topKFrequent(input, 1);

        assertThat(result).containsExactlyInAnyOrder(-1);
    }

    @Test
    void test4() {
        int[] input = new int[]{1, 2};

        int[] result = topKFrequent(input, 2);

        assertThat(result).containsExactlyInAnyOrder(1, 2);
    }
}
