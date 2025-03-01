package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class m_3_SortColors {

    // Counting Sort impl
    public void sortColors(int[] nums) {

        int[] counts = new int[3];

        for (int currentClr : nums) {
            counts[currentClr] = counts[currentClr] + 1;
        }

        int currentPtr = 0;
        for (int i = 0; i < counts.length; i++) {

            for (int j = 0; j < counts[i]; j++) {
                nums[currentPtr] = i;
                currentPtr++;
            }
        }
    }

    @Test
    void test0() {
        int[] input = new int[]{2, 0, 2, 1, 1, 0};

        sortColors(input);

        assertThat(input).isEqualTo(new int[]{0, 0, 1, 1, 2, 2});
    }

    @Test
    void test1() {
        int[] input = new int[]{2, 0, 1};

        sortColors(input);

        assertThat(input).isEqualTo(new int[]{0, 1, 2});
    }

    @Test
    void test2() {
        int[] input = new int[]{0, 1, 2};

        sortColors(input);

        assertThat(input).isEqualTo(new int[]{0, 1, 2});
    }

    @Test
    void test3() {
        int[] input = new int[]{0};

        sortColors(input);

        assertThat(input).isEqualTo(new int[]{0});
    }
}
