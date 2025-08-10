package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * Uses fast exponentiation (exponentiation by squaring) for O(log n) time complexity.
 */
public class m_4_PowXandN {

    /**
     * Calculate x^n using fast exponentiation algorithm.
     * Handles negative exponents by computing positive power then taking reciprocal.
     * 
     * @param x the base
     * @param n the exponent (can be negative)
     * @return x raised to the power n
     */
    public double myPow(double x, int n) {
        // Convert to long to handle Integer.MIN_VALUE overflow when taking absolute value
        double result = helperPow(x, Math.abs((long) n));

        // For negative exponents: x^(-n) = 1/(x^n)
        if (n < 0) {
            result = 1.0 / result;
        }

        return result;
    }

    /**
     * Recursive helper implementing fast exponentiation algorithm.
     * Key insight: x^n = (x^(n/2))^2 when n is even, or (x^(n/2))^2 * x when n is odd.
     * This reduces the problem size by half at each step, achieving O(log n) complexity.
     * 
     * @param x the base
     * @param n the exponent (non-negative)
     * @return x raised to the power n
     */
    private double helperPow(double x, long n) {
        // Base cases
        if (x == 0) {
            return 0;
        }

        if (n == 0) {
            return 1; // Any number to power 0 equals 1
        }

        if (n == 1) {
            return x;
        }

        // Divide exponent by 2 using bit shift (faster than division)
        long divN = n >> 1;

        // Recursively calculate x^(n/2)
        double divRes = helperPow(x, divN);

        // Square the result: (x^(n/2))^2 = x^n when n is even
        double result = divRes * divRes;

        // If n is odd, multiply by x one more time
        // Use bitwise AND to check if number is odd (faster than modulo)
        if ((n & 1) == 1) {
            result = result * x;
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(myPow(2, 10)).isEqualTo(1024);
    }

    @Test
    void test1() {
        assertThat(myPow(2.1, 3)).isCloseTo(9.261, within(0.001));
    }

    @Test
    void test2() {
        assertThat(myPow(2, -2)).isEqualTo(0.25);
    }

    @Test
    void test3() {
        assertThat(myPow(1, -2147483648)).isEqualTo(1);
    }
}
