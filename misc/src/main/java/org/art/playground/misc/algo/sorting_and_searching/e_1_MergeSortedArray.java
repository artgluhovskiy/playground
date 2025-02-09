package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 */
public class e_1_MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int first = m - 1;
        int second = n - 1;
        int insertPos = nums1.length - 1;

        while (first >= 0 && second >= 0) {
            int val1 = nums1[first];
            int val2 = nums2[second];
            if (val2 >= val1) {
                nums1[insertPos] = val2;
                second--;
            } else {
                nums1[insertPos] = val1;
                first--;
            }

            insertPos--;
        }

        while (second >= 0) {
            nums1[insertPos] = nums2[second];
            second--;
            insertPos--;
        }
    }

    @Test
    void test0() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;

        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge(nums1, m, nums2, n);

        assertThat(nums1).isEqualTo(new int[]{1, 2, 2, 3, 5, 6});
    }

    @Test
    void test1() {
        int[] nums1 = {1};
        int m = 1;

        int[] nums2 = {};
        int n = 0;

        merge(nums1, m, nums2, n);

        assertThat(nums1).isEqualTo(new int[]{1});
    }

    @Test
    void test2() {
        int[] nums1 = {0};
        int m = 0;

        int[] nums2 = {1};
        int n = 1;

        merge(nums1, m, nums2, n);

        assertThat(nums1).isEqualTo(new int[]{1});
    }
}
