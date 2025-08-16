package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 */
public class m_5_SqrtOfX {

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int result = 0;

        int left = 1;
        int right = x;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            
            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }

    @Test
    void test0() {
        assertThat(mySqrt(4)).isEqualTo(2);
    }

    @Test
    void test1() {
        assertThat(mySqrt(8)).isEqualTo(2);
    }

    @Test
    void test2() {
        assertThat(mySqrt(6)).isEqualTo(2);
    }

    @Test
    void test3() {
        assertThat(mySqrt(2147395599)).isEqualTo(46339);
    }

    @Test
    void testEdgeCases() {
        assertThat(mySqrt(0)).isEqualTo(0);
        assertThat(mySqrt(1)).isEqualTo(1);
    }

    @Test
    void testPerfectSquares() {
        assertThat(mySqrt(9)).isEqualTo(3);
        assertThat(mySqrt(16)).isEqualTo(4);
        assertThat(mySqrt(25)).isEqualTo(5);
        assertThat(mySqrt(100)).isEqualTo(10);
    }

    @Test
    void testLargeNumbers() {
        assertThat(mySqrt(Integer.MAX_VALUE)).isEqualTo(46340);
        assertThat(mySqrt(2147395600)).isEqualTo(46340);
    }
}
