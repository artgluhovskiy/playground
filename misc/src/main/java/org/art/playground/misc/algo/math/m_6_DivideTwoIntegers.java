package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 */
public class m_6_DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        // Handle edge case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Handle zero dividend
        if (dividend == 0) {
            return 0;
        }
        
        // Handle same value division
        if (dividend == divisor) {
            return 1;
        }
        
        // Handle divisor of 1 or -1
        if (divisor == 1) return dividend;
        if (divisor == -1) return -dividend;
        
        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // Convert to negative to avoid overflow issues
        int absDividend = dividend == Integer.MIN_VALUE ? Integer.MIN_VALUE : -Math.abs(dividend);
        int absDivisor = divisor == Integer.MIN_VALUE ? Integer.MIN_VALUE : -Math.abs(divisor);
        
        int result = 0;
        
        // Find the highest power of 2 that can be subtracted
        while (absDividend <= absDivisor) {
            int temp = absDivisor;
            int multiple = 1;
            
            // Double temp until it's too large, using safe bit shifting
            while (temp >= Integer.MIN_VALUE / 2 && absDividend <= temp + temp) {
                // Safe to shift left by 1 (equivalent to *2)
                temp = temp << 1;
                multiple = multiple << 1;
            }
            
            absDividend -= temp;
            result += multiple;
        }
        
        return negative ? -result : result;
    }

    @Test
    void test0() {
        assertThat(divide(10, 3)).isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(divide(7, -3)).isEqualTo(-2);
    }

    @Test
    void test2() {
        assertThat(divide(-2147483648, -1)).isEqualTo(2147483647);
    }

    @Test
    void test3() {
        assertThat(divide(-2147483648, -2147483648)).isEqualTo(1);
    }

    @Test
    void testOverflow() {
        assertThat(divide(Integer.MIN_VALUE, -1)).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void testEdgeCases() {
        assertThat(divide(0, 1)).isEqualTo(0);
        assertThat(divide(1, 1)).isEqualTo(1);
        assertThat(divide(-1, 1)).isEqualTo(-1);
        assertThat(divide(1, -1)).isEqualTo(-1);
    }

    @Test
    void testLargeNumbers() {
        assertThat(divide(Integer.MAX_VALUE, 1)).isEqualTo(Integer.MAX_VALUE);
        assertThat(divide(Integer.MAX_VALUE, 2)).isEqualTo(1073741823);
    }

    @Test
    void testImprovedEdgeCases() {
        assertThat(divide(0, 5)).isEqualTo(0);
        assertThat(divide(42, 42)).isEqualTo(1);
        assertThat(divide(100, 1)).isEqualTo(100);
        assertThat(divide(-100, 1)).isEqualTo(-100);
        assertThat(divide(100, -1)).isEqualTo(-100);
        assertThat(divide(-100, -1)).isEqualTo(100);
    }
}
