package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class e_14_MeetingRoom {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 1) {
            return true;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] >= intervals[i][0]) {
                return false;
            }
        }

        return true;
    }

    @Test
    void test0() {
        int[][] input = {
            new int[]{0, 30},
            new int[]{5, 10},
            new int[]{15, 20}
        };

        assertThat(canAttendMeetings(input)).isEqualTo(false);
    }

    @Test
    void test1() {
        int[][] input = {
            new int[]{0, 30},
            new int[]{35, 40},
            new int[]{45, 50}
        };

        assertThat(canAttendMeetings(input)).isEqualTo(true);
    }

}
