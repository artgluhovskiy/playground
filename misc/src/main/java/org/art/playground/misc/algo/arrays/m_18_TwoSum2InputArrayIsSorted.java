package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class m_18_TwoSum2InputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return new int[]{-1, -1};
    }

    @Test
    void test0() {
        assertThat(twoSum(new int[]{2, 7, 11, 15}, 9))
            .isEqualTo(new int[]{1, 2});
    }

    @Test
    void test1() {
        assertThat(twoSum(new int[]{2, 3, 4}, 6))
            .isEqualTo(new int[]{1, 3});
    }

    @Test
    void test2() {
        assertThat(twoSum(new int[]{-1, 0}, -1))
            .isEqualTo(new int[]{1, 2});
    }
}
