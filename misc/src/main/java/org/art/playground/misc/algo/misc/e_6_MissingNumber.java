package org.art.playground.misc.algo.misc;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 */
public class e_6_MissingNumber {

    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res = res + i - nums[i];
        }
        return res;
    }

    @Test
    void test0() {
        assertThat(missingNumber(new int[] {3, 0, 1})).isEqualTo(2);
    }

    @Test
    void test1() {
        assertThat(missingNumber(new int[] {0, 1})).isEqualTo(2);
    }

    @Test
    void test2() {
        assertThat(missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1})).isEqualTo(8);
    }

    @Test
    void test3() {
        assertThat(missingNumber(new int[] {2, 0})).isEqualTo(1);
    }
}
