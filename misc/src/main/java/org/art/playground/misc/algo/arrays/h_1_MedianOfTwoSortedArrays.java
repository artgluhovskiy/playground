package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 */
public class h_1_MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int totalLength = nums1.length + nums2.length;
        int halfLength = totalLength / 2;

        boolean isEven = totalLength % 2 == 0;

        int from = 0;
        int to = nums1.length - 1;

        while (true) {

            int num1mid = to >= 0 ? from + (to - from) / 2 : -1; // Handle edje case, when the first array has 0-length, so that to = -2
            int num2mid = halfLength - (num1mid + 1) - 1;

            double nums1left = num1mid >= 0 ? nums1[num1mid] : Double.NEGATIVE_INFINITY;
            double nums1right = num1mid + 1 < nums1.length ? nums1[num1mid + 1] : Double.POSITIVE_INFINITY;

            double nums2left = num2mid >= 0 ? nums2[num2mid] : Double.NEGATIVE_INFINITY;
            double nums2right = num2mid + 1 < nums2.length ? nums2[num2mid + 1] : Double.POSITIVE_INFINITY;

            if (nums1left <= nums2right && nums2left <= nums1right) {
                if (isEven) {
                    return (Math.max(nums1left, nums2left) + Math.min(nums1right, nums2right)) / (double) 2;
                } else {
                    return Math.min(nums1right, nums2right);
                }
            } else if (nums1left > nums2right) {
                to = num1mid - 1;
            } else {
                from = num1mid + 1;
            }
        }
    }

    @Test
    void test0() {
        assertThat(findMedianSortedArrays(
            new int[]{1, 3},
            new int[]{2})
        ).isEqualTo(2);
    }

    @Test
    void test2() {
        assertThat(findMedianSortedArrays(
            new int[]{1, 2},
            new int[]{3, 4})
        ).isEqualTo(2.5);
    }

    @Test
    void test3() {
        assertThat(findMedianSortedArrays(
            new int[]{1, 5, 10, 11},
            new int[]{3, 8, 9})
        ).isEqualTo(8);
    }

    @Test
    void test4() {
        assertThat(findMedianSortedArrays(
            new int[]{3, 8, 9},
            new int[]{1, 5, 10, 11})
        ).isEqualTo(8);
    }

    @Test
    void test5() {
        assertThat(findMedianSortedArrays(
            new int[]{1, 5, 10, 11, 12},
            new int[]{3, 8, 9})
        ).isEqualTo(8.5);
    }

    @Test
    void test6() {
        assertThat(findMedianSortedArrays(
            new int[]{1, 6, 10, 11, 12},
            new int[]{3, 13, 14})
        ).isEqualTo(10.5);
    }

    @Test
    void test7() {
        assertThat(findMedianSortedArrays(
            new int[]{2},
            new int[]{})
        ).isEqualTo(2);
    }

    @Test
    void test8() {
        assertThat(findMedianSortedArrays(
            new int[]{},
            new int[]{2})
        ).isEqualTo(2);
    }

    @Test
    void test9() {
        assertThat(findMedianSortedArrays(
            new int[]{1, 2, 3},
            new int[]{})
        ).isEqualTo(2);
    }

    @Test
    void test10() {
        assertThat(findMedianSortedArrays(
            new int[]{2},
            new int[]{1, 3})
        ).isEqualTo(2);
    }
}
