package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of meeting time interval objects consisting of start
 * and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i),
 * find the minimum number of days required to schedule all meetings without any conflicts.
 */
public class m_5_MeetingRoom2 {

    // Line Sweep Algorithm
    public int minMeetingRooms(List<Interval> intervals) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (Interval interval : intervals) {
            tm.put(interval.start, tm.getOrDefault(interval.start, 0) + 1);
            tm.put(interval.end, tm.getOrDefault(interval.end, 0) - 1);
        }

        int prev = 0;
        int max = 0;
        for (Integer i : tm.keySet()) {
            prev = prev + tm.get(i);
            max = Math.max(prev, max);
        }

        return max;
    }

    // Min Heap solution
    public int minMeetingRoomsV2(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.start));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Interval interval : intervals) {
            if (!pq.isEmpty() && pq.peek() < interval.start) {
                pq.poll();
            }
            pq.offer(interval.end);
        }
        return pq.size();
    }

    public int minMeetingRoomsV3(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.start));

        int curIntersection = 1;
        int maxIntersections = curIntersection;

        for (int i = 1; i < intervals.size(); i++) {
            Interval interval1 = intervals.get(i - 1);
            Interval interval2 = intervals.get(i);

            if (interval1.end > interval2.start) {
                curIntersection++;

                if (curIntersection > maxIntersections) {
                    maxIntersections = curIntersection;
                }
            }
        }

        return maxIntersections;
    }

    @Test
    void test1() {
        var input = new ArrayList<>(
            List.of(
                new Interval(0, 40),
                new Interval(5, 10),
                new Interval(15, 20)
            ));

        assertThat(minMeetingRooms(input)).isEqualTo(2);
    }

    @Test
    void test2() {
        var input = new ArrayList<>(
            List.of(
                new Interval(0, 40)
            )
        );

        assertThat(minMeetingRooms(input)).isEqualTo(1);
    }

    private static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
