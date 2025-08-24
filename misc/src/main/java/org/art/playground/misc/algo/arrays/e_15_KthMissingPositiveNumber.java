package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * <p>
 * Return the kth positive integer that is missing from this array.
 */
public class e_15_KthMissingPositiveNumber {

    // Simple O(n + k) solution.
    public int findKthPositiveV1(int[] arr, int k) {
        int ptr = 0;
        int missingCounter = 0;
        for (int i = 1; i <= arr.length + k; i++) {
            if (ptr >= arr.length || arr[ptr] != i) {
                missingCounter++;
                if (missingCounter == k) {
                    return i;
                }
            } else {
                ptr++;
            }
        }
        throw new IllegalStateException();
    }

    // Binary search O(log n) solution.
    /*
        E.g. k = 5

        0  1  2  3  4   - indexes
        2  3  4  7  11  - values
        1  1  1  3  6   - missing values before i
                 l  h

        Missing values before index
        miss_before_i = val_i - (i + 1)

        Answer: = val_l + (k - miss_before_l)
                = val_l + (k - (val_l - (l + 1)))
                = val_l + k - val_l + l + 1)
                = l + k + 1 (!)
     */
    public int findKthPositiveV2(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - mid - 1;

            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Since 'high' became lower than 'low'
        low = high;

        // Check, how the formula was derived
        return low + k + 1;
    }

    @Test
    void test0() {
        assertThat(findKthPositiveV1(new int[]{2, 3, 4, 7, 11}, 5))
            .isEqualTo(9);
    }

    @Test
    void test1() {
        assertThat(findKthPositiveV1(new int[]{1, 2, 3, 4}, 2))
            .isEqualTo(6);
    }

    @Test
    void test2() {
        assertThat(findKthPositiveV1(new int[]{5, 6}, 2))
            .isEqualTo(2);
    }

    @Test
    void test3() {
        assertThat(findKthPositiveV1(new int[]{2, 3}, 4))
            .isEqualTo(6);
    }

    @Test
    void test4() {
        assertThat(findKthPositiveV1(new int[]{2, 3}, 4))
            .isEqualTo(6);
    }

    @Test
    void test5() {
        assertThat(findKthPositiveV1(new int[]{3, 10}, 2))
            .isEqualTo(2);
    }

    @Test
    void test6() {
        assertThat(findKthPositiveV1(new int[]{3}, 3))
            .isEqualTo(4);
    }
}
