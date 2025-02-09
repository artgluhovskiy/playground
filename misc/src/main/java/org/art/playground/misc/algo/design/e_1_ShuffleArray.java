package org.art.playground.misc.algo.design;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 * All permutations of the array should be equally likely as a result of the shuffling.
 */
public class e_1_ShuffleArray {

    static class Solution {

        private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();

        private int[] nums;

        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int[] reset() {
            return this.nums;
        }

        public int[] shuffle() {
            int[] copy = new int[nums.length];

            System.arraycopy(this.nums, 0, copy, 0, this.nums.length);

            for (int i = copy.length - 1; i > 0; i--) {
                int currentVal = copy[i];
                int rnd = TLR.nextInt(0, i);
                copy[i] = copy[rnd];
                copy[rnd] = currentVal;
            }

            return copy;
        }
    }

    @Test
    void test0() {
        var s = new Solution(new int[]{1, 2, 3});
        System.out.println(Arrays.toString(s.reset()));
        System.out.println(Arrays.toString(s.shuffle()));
        System.out.println(Arrays.toString(s.reset()));
        System.out.println(Arrays.toString(s.shuffle()));
    }

}
