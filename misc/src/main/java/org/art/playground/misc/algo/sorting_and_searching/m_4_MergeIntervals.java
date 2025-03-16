package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals that cover
 * all the intervals in the input.
 */
public class m_4_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> results = new LinkedList<>();

        for (int[] interval : intervals) {
            if (results.isEmpty()) {
                results.add(interval);
            } else if (results.getLast()[1] >= interval[0]) {
                results.getLast()[1] = Math.max(results.getLast()[1], interval[1]);
            } else {
                results.add(interval);
            }
        }

        return results.toArray(new int[results.size()][]);
    }

    @Test
    void test0() {
        int[][] input = {
            new int[]{1, 3},
            new int[]{2, 6},
            new int[]{8, 10},
            new int[]{8, 10},
            new int[]{15, 18}
        };

        assertThat(merge(input)).isEqualTo(
            new int[][]{
                new int[]{1, 6},
                new int[]{8, 10},
                new int[]{15, 18}
            });
    }

    @Test
    void test1() {
        int[][] input = {
            new int[]{1, 4},
            new int[]{4, 5}
        };

        assertThat(merge(input)).isEqualTo(
            new int[][]{
                new int[]{1, 5}
            });
    }

    @Test
    void test2() {
        int[][] input = {
            new int[]{1, 4},
            new int[]{0, 4}
        };

        assertThat(merge(input)).isEqualTo(
            new int[][]{
                new int[]{0, 4}
            });
    }

    @Test
    void test3() {
        int[][] input = {
            new int[]{1, 4},
            new int[]{2, 3}
        };

        assertThat(merge(input)).isEqualTo(
            new int[][]{
                new int[]{1, 4}
            });
    }

}
