package org.art.playground.misc.algo.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays
 * and you may return the result in any order.
 */
public class e_6_IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] smaller;
        int[] larger;
        if (nums1.length < nums2.length) {
            smaller = nums1;
            larger = nums2;
        } else {
            smaller = nums2;
            larger = nums1;
        }

        Map<Integer, Integer> stats = buildArrayStats(smaller);

        List<Integer> result = new ArrayList<>();
        for (int value : larger) {
            Integer valueCount = stats.get(value);
            if (valueCount != null && valueCount > 0) {
                stats.put(value, valueCount - 1);
                result.add(value);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private static Map<Integer, Integer> buildArrayStats(int[] smaller) {
        Map<Integer, Integer> stats = new HashMap<>();
        for (int value : smaller) {
            stats.merge(value, 1, Integer::sum);
        }
        return stats;
    }

    @Test
    void test0() {
        int[] input1 = {1, 2, 2, 1};
        int[] input2 = {2, 2};
        int[] expected = {2, 2};

        assertThat(intersect(input1, input2)).isEqualTo(expected);
    }

    @Test
    void test1() {
        int[] input1 = {4, 9, 5};
        int[] input2 = {9, 4, 9, 8, 4};
        int[] expected = {9, 4};

        assertThat(intersect(input1, input2)).isEqualTo(expected);
    }
}
