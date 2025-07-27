package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 * <p>
 * Mathematical insight: Trailing zeros in n! are caused by factors of 10 (2 * 5).
 * Since there are always more factors of 2 than 5 in factorial, we only need to count factors of 5.
 * For each power of 5 (5, 25, 125, ...), we count how many multiples of that power exist in n!.
 */
public class m_2_FactorialTrailingZeroes {

    /**
     * Counts the number of trailing zeros in n!.
     * 
     * @param n the number to calculate factorial trailing zeros for
     * @return the number of trailing zeros in n!
     */
    public int trailingZeroes(int n) {
        int trailingZeroCount = 0;
        
        // Count factors of 5, 25, 125, 625, etc.
        // Each power of 5 contributes additional trailing zeros
        for (int powerOfFive = 5; powerOfFive <= n; powerOfFive *= 5) {
            trailingZeroCount += n / powerOfFive;
        }
        
        return trailingZeroCount;
    }

    @Test
    void testSmallNumbers() {
        assertThat(trailingZeroes(0)).isEqualTo(0);
        assertThat(trailingZeroes(1)).isEqualTo(0);
        assertThat(trailingZeroes(3)).isEqualTo(0);
        assertThat(trailingZeroes(4)).isEqualTo(0);
    }

    @Test
    void testNumbersWithSingleFactorOfFive() {
        assertThat(trailingZeroes(5)).isEqualTo(1);
        assertThat(trailingZeroes(6)).isEqualTo(1);
        assertThat(trailingZeroes(7)).isEqualTo(1);
        assertThat(trailingZeroes(8)).isEqualTo(1);
        assertThat(trailingZeroes(9)).isEqualTo(1);
    }

    @Test
    void testNumbersWithMultipleFactorsOfFive() {
        assertThat(trailingZeroes(10)).isEqualTo(2);  // 10 = 2 * 5
        assertThat(trailingZeroes(15)).isEqualTo(3);  // 15 = 3 * 5
        assertThat(trailingZeroes(20)).isEqualTo(4);  // 20 = 4 * 5
        assertThat(trailingZeroes(25)).isEqualTo(6);  // 25 = 5 * 5 (contributes 2 factors)
    }

    @Test
    void testLargerNumbers() {
        assertThat(trailingZeroes(30)).isEqualTo(7);
        assertThat(trailingZeroes(50)).isEqualTo(12);
        assertThat(trailingZeroes(100)).isEqualTo(24);
    }
}
