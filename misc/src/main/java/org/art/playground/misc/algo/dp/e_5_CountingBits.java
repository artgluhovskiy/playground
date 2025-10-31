package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 *
 * <p>Algorithm: Dynamic Programming with Bit Manipulation
 *
 * <p>Key Insight: Any number can be decomposed as 2^k + remainder, where:
 * - 2^k is the highest power of 2 that is <= the number
 * - remainder is what's left after subtracting 2^k
 *
 * <p>Example: 5 = 4 + 1 = 2^2 + 1 (binary: 101)
 * - The highest bit contributes 1
 * - The remainder (1) has already been computed and contributes 1
 * - Total: 1 + 1 = 2 bits
 *
 * <p>Time Complexity: O(n)
 * <p>Space Complexity: O(n)
 */
public class e_5_CountingBits {

    public int[] countBits(int n) {
        if (n == 0) {
            return new int[]{0};
        }

        if (n == 1) {
            return new int[]{0, 1};
        }

        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;

        int power = 1;

        for (int i = 2; i <= n; i++) {
            if ((1 << (power + 1)) == i) {
                power++;
            }

            int remainder = i - (1 << power);

            if (remainder == 0) {
                result[i] = 1;
            } else {
                result[i] = 1 + result[remainder];
            }
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(countBits(2))
            .isEqualTo(new int[]{0, 1, 1});
    }

    @Test
    void test1() {
        assertThat(countBits(5))
            .isEqualTo(new int[]{0, 1, 1, 2, 1, 2});
    }
}
