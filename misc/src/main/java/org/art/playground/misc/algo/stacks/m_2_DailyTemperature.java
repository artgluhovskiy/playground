package org.art.playground.misc.algo.stacks;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day
 * to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */
public class m_2_DailyTemperature {

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1) {
            return new int[]{0};
        }

        int[] results = new int[temperatures.length];

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int i = 1; i < temperatures.length; i++) {

            while (!stack.isEmpty() && temperatures[stack.peekFirst()] < temperatures[i]) {
                Integer top = stack.pop();
                results[top] = i - top;
            }

            stack.push(i);
        }

        return results;
    }

    @Test
    void test0() {
        int[] input = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = new int[]{1, 1, 4, 2, 1, 1, 0, 0};

        assertThat(dailyTemperatures(input)).isEqualTo(expected);
    }

    @Test
    void test1() {
        int[] input = new int[]{30, 40, 50, 60};
        int[] expected = new int[]{1, 1, 1, 0};

        assertThat(dailyTemperatures(input)).isEqualTo(expected);
    }


    @Test
    void test2() {
        int[] input = new int[]{30, 60, 90};
        int[] expected = new int[]{1, 1, 0};

        assertThat(dailyTemperatures(input)).isEqualTo(expected);
    }
}


