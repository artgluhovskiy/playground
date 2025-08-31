package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * NOTE: O(n) time and O(1) space!
 */
public class m_13_MajorityElement {

    public int majorityElement(int[] nums) {
        int count = 0;
        int res = -1;

        for (int num : nums) {
            if (count == 0) {
                res = num;
            }

            if (num == res) {
                count++;
            } else {
                count--;
            }
        }

        return res;
    }

    @Test
    void test0() {
        assertThat(majorityElement(new int[]{3, 2, 3}))
            .isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}))
            .isEqualTo(2);
    }
}
