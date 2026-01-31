package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start
 * and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 */
public class m_7_InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{
                newInterval
            };
        }

        int k = 0;
        Deque<int[]> processedIntervals = new ArrayDeque<>();

        while (k < intervals.length && (intervals[k][1] < newInterval[0])) {
            processedIntervals.addLast(intervals[k]);
            k++;
        }

        while (k < intervals.length && (!isNotOverlap(intervals[k], newInterval))) {
            newInterval[0] = Math.min(intervals[k][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[k][1], newInterval[1]);
            k++;
        }

        processedIntervals.addLast(newInterval);

        while (k < intervals.length) {
            processedIntervals.addLast(intervals[k]);
            k++;
        }

        return processedIntervals.toArray(new int[processedIntervals.size()][2]);
    }

    private boolean isNotOverlap(int[] oldInterval, int[] newInterval) {
        return oldInterval[1] < newInterval[0] || newInterval[1] < oldInterval[0];
    }

    @Test
    void test0() {
        int[][] intervals = {
            new int[]{1, 3},
            new int[]{6, 9}
        };

        int[] newInterval = new int[]{2, 5};

        assertThat(insert(intervals, newInterval)).isEqualTo(
            new int[][]{
                new int[]{1, 5},
                new int[]{6, 9}
            });
    }

    @Test
    void test1() {
        int[][] intervals = {
            new int[]{1, 2},
            new int[]{3, 5},
            new int[]{6, 7},
            new int[]{8, 10},
            new int[]{12, 16},
        };

        int[] newInterval = new int[]{4, 8};

        assertThat(insert(intervals, newInterval)).isEqualTo(
            new int[][]{
                new int[]{1, 2},
                new int[]{3, 10},
                new int[]{12, 16},
            });
    }

    @Test
    void test2() {
        int[][] intervals = {
            new int[]{1, 5}
        };

        int[] newInterval = new int[]{2, 7};

        assertThat(insert(intervals, newInterval)).isEqualTo(
            new int[][]{
                new int[]{1, 7}
            });
    }

}
