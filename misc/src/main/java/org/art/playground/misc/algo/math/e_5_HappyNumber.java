package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Happy Number Problem
 * <p>
 * A happy number is a number defined by the following process:
 * 1. Starting with any positive integer
 * 2. Replace the number by the sum of the squares of its digits
 * 3. Repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1
 * <p>
 * Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Example: 19 is a happy number
 * 1² + 9² = 1 + 81 = 82
 * 8² + 2² = 64 + 4 = 68
 * 6² + 8² = 36 + 64 = 100
 * 1² + 0² + 0² = 1 + 0 + 0 = 1
 */
public class e_5_HappyNumber {

    /**
     * Determines if a number is happy using Floyd's Cycle Detection Algorithm.
     * <p>
     * This approach uses two pointers (slow and fast) to detect cycles.
     * If we reach 1, the number is happy. If we detect a cycle without reaching 1,
     * the number is not happy.
     * <p>
     * Time Complexity: O(log n) - the number of digits in n
     * Space Complexity: O(1) - constant space
     *
     * @param n the number to check
     * @return true if the number is happy, false otherwise
     */
    public boolean isHappy(int n) {
        // Use Floyd's cycle detection with two pointers
        int slowPointer = n;
        int fastPointer = n;

        do {
            // Move slow pointer by one step
            slowPointer = calculateSumOfSquaredDigits(slowPointer);

            // Move fast pointer by two steps
            fastPointer = calculateSumOfSquaredDigits(calculateSumOfSquaredDigits(fastPointer));

            // If either pointer reaches 1, we found a happy number
            if (slowPointer == 1 || fastPointer == 1) {
                return true;
            }

        } while (slowPointer != fastPointer); // Continue until cycle is detected

        // If we detect a cycle without reaching 1, the number is not happy
        return false;
    }

    /**
     * Determines if a number is happy using a HashSet to track visited numbers.
     * <p>
     * This approach keeps track of all numbers we've seen during the process.
     * If we encounter a number we've seen before, we've detected a cycle.
     * <p>
     * Time Complexity: O(log n) - the number of digits in n
     * Space Complexity: O(log n) - to store visited numbers
     *
     * @param n the number to check
     * @return true if the number is happy, false otherwise
     */
    public boolean isHappyV2(int n) {
        Set<Integer> visitedNumbers = new HashSet<>();

        while (!visitedNumbers.contains(n)) {
            visitedNumbers.add(n);

            n = calculateSumOfSquaredDigits(n);
            if (n == 1) {
                return true;
            }
        }

        // If we've seen this number before, we're in a cycle
        return false;
    }

    /**
     * Calculates the sum of the squares of each digit in a number.
     * <p>
     * For example: 123 becomes 1² + 2² + 3² = 1 + 4 + 9 = 14
     *
     * @param number the number to process
     * @return the sum of squared digits
     */
    private int calculateSumOfSquaredDigits(int number) {
        int sumOfSquares = 0;

        while (number > 0) {
            int currentDigit = number % 10;  // Extract the last digit
            sumOfSquares += currentDigit * currentDigit;  // Add the square of the digit
            number /= 10;  // Remove the last digit
        }

        return sumOfSquares;
    }

    @Test
    void testHappyNumber() {
        // 19 is a happy number: 1²+9²=82 → 8²+2²=68 → 6²+8²=100 → 1²+0²+0²=1
        assertThat(isHappy(19)).isTrue();
    }

    @Test
    void testUnhappyNumber() {
        // 2 is not a happy number: 2²=4 → 4²=16 → 1²+6²=37 → 3²+7²=58 → 5²+8²=89 → 8²+9²=145 → ...
        assertThat(isHappy(2)).isFalse();
    }

    @Test
    void testSingleDigitHappyNumbers() {
        // 1 and 7 are the only single-digit happy numbers
        assertThat(isHappy(1)).isTrue();
        assertThat(isHappy(7)).isTrue();
    }

    @Test
    void testSingleDigitUnhappyNumbers() {
        // Other single digits are not happy
        assertThat(isHappy(0)).isFalse();
        assertThat(isHappy(2)).isFalse();
        assertThat(isHappy(3)).isFalse();
        assertThat(isHappy(4)).isFalse();
        assertThat(isHappy(5)).isFalse();
        assertThat(isHappy(6)).isFalse();
        assertThat(isHappy(8)).isFalse();
        assertThat(isHappy(9)).isFalse();
    }

    @Test
    void testBothImplementationsGiveSameResult() {
        int[] testNumbers = {1, 2, 7, 19, 20, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100};

        for (int num : testNumbers) {
            assertThat(isHappy(num)).isEqualTo(isHappyV2(num))
                .as("Both implementations should give the same result for number: " + num);
        }
    }
}

