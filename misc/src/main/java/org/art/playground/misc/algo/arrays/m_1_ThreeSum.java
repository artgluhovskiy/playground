package org.art.playground.misc.algo.arrays;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums, return all the triplets
 * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 */
public class m_1_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int currentVal = nums[i];
            if (i > 0 && currentVal == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int leftVal = nums[left];
                int rightVal = nums[right];
                int sum = currentVal + leftVal + rightVal;

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(List.of(currentVal, leftVal, rightVal));
                    left++;
                    right--;

                    while (nums[left] == nums[left - 1] && left < right) {
                        left++;
                    }
                }
            }
        }

        return result;
    }

    @Test
    void test0() {
        int[] input = {-1, 0, 1, 2, -1, -4};

        assertThat(threeSum(input)).isEqualTo(List.of(
            List.of(-1, -1, 2),
            List.of(-1, 0, 1)
        ));
    }

    @Test
    void test1() {
        int[] input = {0, 1, 1};

        assertThat(threeSum(input)).isEqualTo(List.of());
    }

    @Test
    void test2() {
        int[] input = {0, 0, 0};

        assertThat(threeSum(input)).isEqualTo(List.of(List.of(0, 0, 0)));
    }
}
