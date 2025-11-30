package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Coding problem: Longest Consecutive Sequence
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 */
public class m_17_LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }

        int maxLength = 0;

        for (int num : numSet) {
            // Only start counting from sequence beginnings
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    @Test
    void test0() {
        int[] input = {100, 4, 200, 1, 3, 2};

        assertThat(longestConsecutive(input)).isEqualTo(4);
    }

    @Test
    void test1() {
        int[] input = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        assertThat(longestConsecutive(input)).isEqualTo(9);
    }

    @Test
    void test2() {
        int[] input = {1, 0, 1, 2};

        assertThat(longestConsecutive(input)).isEqualTo(3);
    }

    @Test
    void test3() {
        int[] input = {12};

        assertThat(longestConsecutive(input)).isEqualTo(1);
    }

    @Test
    void test4() {
        int[] input = {1, 2, 3, 4, 5};

        assertThat(longestConsecutive(input)).isEqualTo(5);
    }
}
