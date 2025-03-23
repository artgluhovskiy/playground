package org.art.playground.misc.algo.arrays;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class e_12_FindMissingObservations {

    private static final int[] EMPTY = new int[] {};

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int totalRolls = rolls.length + n;

        int knownRollsSum = 0;
        for (int roll : rolls) {
            knownRollsSum += roll;
        }

        int remainingSum = mean * totalRolls - knownRollsSum;

        if (remainingSum > n * 6 || remainingSum < n) {
            return EMPTY;
        }

        int[] result = new int[n];

        int distributedSum = remainingSum / n;
        int reminder = remainingSum % n;

        Arrays.fill(result, distributedSum);

        for (int i = 0; i < reminder; i++) {
            result[i]++;
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(missingRolls(new int[] {3, 2, 4, 3}, 4, 2))
            .isEqualTo(new int[] {6, 6});
    }

    @Test
    void test1() {
        assertThat(missingRolls(new int[] {6, 3, 4, 3, 5, 3}, 1, 6))
            .isEqualTo(new int[] {});
    }

//    @Test
    void test2() {
        assertThat(missingRolls(new int[] {1, 5, 6}, 3, 4))
            .isEqualTo(new int[] {2, 3, 2, 2});
    }

    @Test
    void test3() {
        assertThat(missingRolls(new int[] {1, 2, 3, 4}, 6, 4))
            .isEqualTo(new int[] {});
    }

//    @Test
    void test4() {
        assertThat(missingRolls(new int[] {4, 5, 6, 2, 3, 6, 5, 4, 6, 4, 5, 1, 6, 3, 1, 4, 5, 5, 3, 2, 3, 5, 3, 2, 1, 5, 4, 3, 5, 1, 5}, 4, 40))
            .isEqualTo(new int[] {4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5});
    }
}
