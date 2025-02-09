package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class e_1_ClimbingStairs {

    public int climbStairsV1(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int[] climbs = new int[n];
        climbs[0] = 1;
        climbs[1] = 2;

        for (int i = 2; i < n; i++) {
            climbs[i] = climbs[i - 1] + climbs[i - 2];
        }

        return climbs[n - 1];
    }

    public int climbStairsV2(int n) {
        if (n == 1) {
            return 1;
        }

        int current = 1;
        int previous = 1;

        for (int i = 1; i < n; i++) {
            int temp = current;
            current = current + previous;
            previous = temp;

        }

        return current;
    }

    @Test
    void test0() {
        var input = 2;

        assertThat(climbStairsV2(input)).isEqualTo(2);
    }

    @Test
    void test1() {
        var input = 3;

        assertThat(climbStairsV2(input)).isEqualTo(3);
    }
}
