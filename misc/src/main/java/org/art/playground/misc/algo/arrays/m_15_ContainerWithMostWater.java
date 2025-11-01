package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * Two pointers approach is used.
 */
public class m_15_ContainerWithMostWater {

    public int maxArea(int[] heights) {
        int maxSeenArea = -1;

        int left = 0;
        int right = heights.length - 1;

        while (left < right) {

            int width = right - left;
            int height = Math.min(heights[left], heights[right]);
            int area = width * height;

            if (area > maxSeenArea) {
                maxSeenArea = area;
            }

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxSeenArea;
    }

    @Test
    void test0() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        assertThat(maxArea(height))
            .isEqualTo(49);
    }

    @Test
    void test1() {
        int[] height = {1, 1};

        assertThat(maxArea(height))
            .isEqualTo(1);
    }
}
