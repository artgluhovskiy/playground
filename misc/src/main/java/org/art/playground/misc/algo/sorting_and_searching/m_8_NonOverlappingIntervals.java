package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Note that intervals which only touch at a point are non-overlapping.
 * For example, [1, 2] and [2, 3] are non-overlapping.
 */
public class m_8_NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        int[] prev = intervals[0];
        int result = 0;

        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];

            if (isOverlapping(prev, currentInterval)) {
                if (currentInterval[1] <= prev[1]) {
                    prev = currentInterval;
                }
                result++;
            } else {
                prev = currentInterval;
            }
        }
        return result;
    }

    private boolean isOverlapping(int[] int1, int[] int2) {
        return int2[0] < int1[1];
    }

    @Test
    void test0() {
        int[][] input = new int[][]{
            {1, 2}, {2, 3}, {3, 4}, {1, 3}
        };
        assertThat(eraseOverlapIntervals(input)).isEqualTo(1);
    }

    @Test
    void test1() {
        int[][] input = new int[][]{
            {1, 2}, {1, 2}, {1, 2}
        };
        assertThat(eraseOverlapIntervals(input)).isEqualTo(2);
    }

    @Test
    void test2() {
        int[][] input = new int[][]{
            {1, 2}, {2, 3}
        };
        assertThat(eraseOverlapIntervals(input)).isZero();
    }
}
