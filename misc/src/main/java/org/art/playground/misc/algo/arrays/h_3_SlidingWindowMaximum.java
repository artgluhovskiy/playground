package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an array of integers nums, there is a sliding window of size k which
 * is moving from the very left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 * Return the max sliding window.
 */
public class h_3_SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // Deque stores indices only; values are nums[index].
        // Front = largest candidate (answer). Back = where new indices enter.
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;

        // Step 0: one loop for every index i (do not seed index 0 and start at i=1).
        for (int i = 0; i < nums.length; i++) {
            int windowLeft = i - k + 1;

            // Step 1 — FRONT: remove indices that slid out of the window.
            while (!deque.isEmpty() && deque.peekFirst() < windowLeft) {
                deque.pollFirst();
            }

            // Step 2 — BACK: remove indices that can never be max while i is in the window.
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Step 3 — BACK: new array element always enters at the back.
            deque.offerLast(i);

            // Step 4 — FRONT: first full window ends at i == k-1; max is at the front.
            if (i >= k - 1) {
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))
            .isEqualTo(new int[]{3, 3, 5, 5, 6, 7});
    }

    @Test
    void test1() {
        assertThat(maxSlidingWindow(new int[]{1}, 1))
            .isEqualTo(new int[]{1});
    }
}
