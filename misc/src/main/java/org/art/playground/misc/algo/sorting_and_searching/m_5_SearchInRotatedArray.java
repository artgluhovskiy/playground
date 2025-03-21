package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class m_5_SearchInRotatedArray {

    public int search(int[] nums, int target) {
        return doSearch(0, nums.length - 1, nums, target);
    }

    private int doSearch(int from, int to, int[] nums, int target) {
        if (from > to) {
            return -1;
        }

        int mid = from + (to - from) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        // in the left-sorted portion
        if (nums[from] <= nums[mid]) {
            if (target < nums[mid]) {
                if (target < nums[from]) {
                    return doSearch(mid + 1, to, nums, target);
                } else {
                    return doSearch(from, mid - 1, nums, target);
                }
            } else {
                return doSearch(mid + 1, to, nums, target);
            }

        // in the right-sorted portion
        } else {
            if (target < nums[mid]) {
                return doSearch(from, mid - 1, nums, target);
            } else {
                if (target > nums[to]) {
                    return doSearch(from, mid - 1, nums, target);
                } else {
                    return doSearch(mid + 1, to, nums, target);
                }
            }
        }
    }

    @Test
    void test0() {
        int[] input = {4, 5, 6, 7, 0, 1, 2, 3};

        assertThat(search(input, 0)).isEqualTo(4);
    }

    @Test
    void test1() {
        int[] input = {4, 5, 6, 7, 0, 1, 2};

        assertThat(search(input, 3)).isEqualTo(-1);
    }

    @Test
    void test2() {
        int[] input = {1};

        assertThat(search(input, 0)).isEqualTo(-1);
    }

    @Test
    void test3() {
        int[] input = {1, 3, 5, 6, 7, 9, 11};

        assertThat(search(input, 9)).isEqualTo(5);
    }

    @Test
    void test4() {
        int[] input = {5, 1, 3};

        assertThat(search(input, 5)).isEqualTo(0);
    }

    @Test
    void test5() {
        int[] input = {3, 1};

        assertThat(search(input, 1)).isEqualTo(1);
    }
}
